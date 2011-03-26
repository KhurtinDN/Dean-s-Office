package ru.sgu.csit.inoc.deansoffice.services.impl;

import freemarker.template.TemplateException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StipendDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.Templater;
import ru.sgu.csit.inoc.deansoffice.services.DirectiveService;
import ru.sgu.csit.inoc.deansoffice.services.StudentService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:33
 */
//@Service
public class DirectiveServiceImpl implements DirectiveService {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    @Override
    public Directive createDirective(String type) {
        Directive directive;

        if (Directive.APPOINT_CAPTAINS.equalsIgnoreCase(type)) {
            directive = new Directive1();
        } else if (Directive.APPOINT_SOCIAL_STIPEND.equalsIgnoreCase(type)) {
            directive = new Directive2();
        } else {
            throw new RuntimeException("Can not create directive with type " + type);
        }

        return directive;
    }

    @Override
    public void executeDirective(Directive directive) {
        StudentDAO studentDAO = applicationContext.getBean(StudentDAO.class);
        GroupDAO groupDAO = applicationContext.getBean(GroupDAO.class);
        StipendDAO stipendDAO = applicationContext.getBean(StipendDAO.class);

        if (Directive.APPOINT_CAPTAINS.equals(directive.getType())) {
            Directive1.SourceData sourceData =
                    (Directive1.SourceData) directive.getData().getSourceData();

            Map<Group, Student> captains = sourceData.getCaptains();
            for (Map.Entry<Group, Student> captainEntry : captains.entrySet()) {
                Group group = groupDAO.findByName(captainEntry.getKey().getName()).get(0);
                List<Student> students = studentDAO.findByGroup(group);
                StudentService studentService = new StudentServiceImpl();

                for (Student student : students) {
                    if (studentService.equalsFullName(student, captainEntry.getValue())) {
                        student.setRole(Student.Role.CAPTAIN);
                        studentDAO.update(student);
                        break;
                    }
                }
            }
        } else if (Directive.APPOINT_SOCIAL_STIPEND.equals(directive.getType())) {
            Directive2.SourceData sourceData =
                    (Directive2.SourceData) directive.getData().getSourceData();

            Map<Student, Stipend> students = sourceData.getStudents();
            for (Map.Entry<Student, Stipend> studentEntry : students.entrySet()) {
                Student student = studentDAO.findById(studentEntry.getKey().getId());
                Stipend targetStipend = studentEntry.getValue();
                boolean stipendAlreadyExist = false;

                for (Stipend stipend : student.getStipends()) {
                    if (stipend.getType() == Stipend.StipendType.SOCIAL) {
                        stipendAlreadyExist = true; // todo action, if social stipend of student already exist
                    }
                }
                if (!stipendAlreadyExist) {
                    List<Stipend> stipends = stipendDAO.findByAllParam(targetStipend.getType(), targetStipend.getStartDate(),
                            targetStipend.getEndDate(), targetStipend.getValue());

                    if (!stipends.isEmpty()) {
                        targetStipend = stipends.get(0);
                    } else {
                        stipendDAO.save(targetStipend);
                    }
                    student.addStipend(targetStipend);
                    studentDAO.update(student);
                }
            }
        } else {
            throw new RuntimeException("Can not create directive with type " + directive.getType());
        }
    }

    @Override
    public String generatePrintTemplateBody(Directive directive) throws UnsupportedEncodingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Map rootMap = new HashMap();

        if (Directive.APPOINT_CAPTAINS.equals(directive.getType())) {
            Directive1.SourceData sourceData =
                    (Directive1.SourceData) directive.getData().getSourceData();

            Map<Group, Student> captains = sourceData.getCaptains();
            Map<String, Integer> specialities = new HashMap<String, Integer>();
            List<SpecialityData> dataList = new ArrayList<SpecialityData>();

            for (Map.Entry<Group, Student> captainEntry : captains.entrySet()) {
                Speciality currentSpeciality = captainEntry.getValue().getSpeciality();
                if (!specialities.containsKey(currentSpeciality.getName())) {
                    SpecialityData newSpecData = new SpecialityData();
                    newSpecData.setName(currentSpeciality.getName());
                    dataList.add(newSpecData);
                    specialities.put(currentSpeciality.getName(), dataList.indexOf(newSpecData));
                }
                dataList.get(specialities.get(currentSpeciality.getName())).addCaptain(
                        new SpecialityData.Captain(captainEntry.getKey(), captainEntry.getValue()));
            }
            rootMap.put("dataList", dataList);
            String simpleTemplName = "templates/directive1.ftl";
            String templDir = DirectiveServiceImpl.class.getResource("/" + simpleTemplName).getPath()
                    .replace("%20", " ");
            templDir = templDir.substring(0, templDir.lastIndexOf("/" + simpleTemplName));
            try {
                Templater templater = new Templater(templDir);

                templater.process(simpleTemplName, rootMap, outputStream);
            } catch (IOException e) {
                throw new RuntimeException("IO Exception in templater.", e);
            } catch (TemplateException e) {
                throw new RuntimeException("Template Exception in templater.", e);
            }
        } else if (Directive.APPOINT_SOCIAL_STIPEND.equals(directive.getType())) {
            Directive2.SourceData sourceData =
                    (Directive2.SourceData) directive.getData().getSourceData();

            Map<Student, Stipend> studentMap = sourceData.getStudents();
            List<Map> studentMaps = new ArrayList<Map>();
            for (Map.Entry<Student, Stipend> studentEntry : studentMap.entrySet()) {
                Map theMap = new HashMap();
                theMap.put("student", studentEntry.getKey());
                theMap.put("stipend", studentEntry.getValue());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                theMap.put("stipendStartDate", dateFormat.format(studentEntry.getValue().getStartDate()));
                theMap.put("stipendEndDate", dateFormat.format(studentEntry.getValue().getEndDate()));
                studentMaps.add(theMap);
            }
            Collections.sort(studentMaps, new Comparator<Map>() {
                public int compare(Map map1, Map map2) {
                    String groupName1 = ((Student) map1.get("student")).getGroup().getName();
                    String groupName2 = ((Student) map2.get("student")).getGroup().getName();

                    return groupName1.compareTo(groupName2);
                }
            });
            rootMap.put("studentMaps", studentMaps);//studentMap.entrySet());
            String simpleTemplName = "templates/directive2.ftl";
            String templDir = DirectiveServiceImpl.class.getResource("/" + simpleTemplName).getPath()
                    .replace("%20", " ");
            templDir = templDir.substring(0, templDir.lastIndexOf("/" + simpleTemplName));
            try {
                Templater templater = new Templater(templDir);

                templater.process(simpleTemplName, rootMap, outputStream);
            } catch (IOException e) {
                throw new RuntimeException("IO Exception in templater.", e);
            } catch (TemplateException e) {
                throw new RuntimeException("Template Exception in templater.", e);
            }
        } else {
            throw new RuntimeException("Can not generate print template with directive type " + directive.getType());
        }

        return outputStream.toString("UTF-8");
    }

    public static class SpecialityData {
        private String name;
        private List<Captain> captains = new ArrayList<Captain>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Captain> getCaptains() {
            return captains;
        }

        public void setCaptains(List<Captain> captains) {
            this.captains = captains;
        }

        public void addCaptain(Captain captain) {
            this.captains.add(captain);
        }

        public static class Captain {
            private Group group;
            private Student student;

            public Captain() {
            }

            public Captain(Group group, Student student) {
                this.group = group;
                this.student = student;
            }

            public Group getGroup() {
                return group;
            }

            public void setGroup(Group group) {
                this.group = group;
            }

            public Student getStudent() {
                return student;
            }

            public void setStudent(Student student) {
                this.student = student;
            }
        }
    }
}