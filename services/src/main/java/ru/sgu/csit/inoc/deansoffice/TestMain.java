package ru.sgu.csit.inoc.deansoffice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.*;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.services.PhotoService;
import ru.sgu.csit.inoc.deansoffice.services.impl.PhotoServiceImpl;

import java.io.IOException;
import java.util.*;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 10, 2010
 * Time: 11:14:30 AM
 */
public class TestMain {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    private StudentDAO studentDAO = applicationContext.getBean(StudentDAO.class);
    private GroupDAO groupDAO = applicationContext.getBean(GroupDAO.class);
    private SpecialityDAO specialityDAO = applicationContext.getBean(SpecialityDAO.class);
    private FacultyDAO facultyDAO = applicationContext.getBean(FacultyDAO.class);
    private DeanDAO deanDAO = applicationContext.getBean(DeanDAO.class);
    private RectorDAO rectorDAO = applicationContext.getBean(RectorDAO.class);
    private EnrollmentOrderDAO enrollmentOrderDAO = applicationContext.getBean(EnrollmentOrderDAO.class);

    private static ParentDAO parentDAO = applicationContext.getBean(ParentDAO.class);
    private static PhotoDAO photoDAO = applicationContext.getBean(PhotoDAO.class);
    private static PassportDAO passportDAO = applicationContext.getBean(PassportDAO.class);
    private static AdditionalStudentDataDAO additionalDataDAO = applicationContext.getBean(AdditionalStudentDataDAO.class);
    private static CoordinatorDAO coordinatorDAO = applicationContext.getBean(CoordinatorDAO.class);
    private static DirectiveDAO directiveDAO = applicationContext.getBean(DirectiveDAO.class);
    private static Directive1DAO directive1DAO = applicationContext.getBean(Directive1DAO.class);
    private static Directive2DAO directive2DAO = applicationContext.getBean(Directive2DAO.class);

    private final Integer COUNT_STUDENTS_IN_GROUP = 10;

    public static void main(String[] args) {
        TestMain testMain = new TestMain();

        testMain.createFaculty();
        testMain.createSpecialitiesAndGroups();
//        testMain.createGroups();
//        testMain.createStudents();
    }

    private void createFaculty() {
        Dean dean = new Dean();
        dean.setFirstName("Антонина");
        dean.setMiddleName("Гавриловна");
        dean.setLastName("Федорова");
        dean.setPosition("Декан факультета КНиИТ");
        dean.setDegree("к.ф.-м.н., доцент");
        deanDAO.save(dean);

        Rector rector = new Rector();
        rector.setFirstName("Леонид");
        rector.setMiddleName("Юрьевич");
        rector.setLastName("Коссович");
        rector.setPosition("Ректор СГУ");
        rector.setDegree("д.ф.-м.н., профессор");
        rectorDAO.save(rector);

        Faculty faculty = new Faculty();
        faculty.setFullName("Компьютерных наук и информационных технологий");
        faculty.setShortName("КНиИТ");
        faculty.setDean(dean);
        faculty.setRector(rector);
        facultyDAO.save(faculty);

        coordinatorDAO.save(new Coordinator("Проректор по учебно-организационной работе"));
        coordinatorDAO.save(new Coordinator("Начальник учебного управления"));
        coordinatorDAO.save(new Coordinator("Начальник юридического отдела"));
        coordinatorDAO.save(new Coordinator("Декан факультета"));
        coordinatorDAO.save(new Coordinator("Начальник общего отдела"));

        Directive1 directive1 = new Directive1();
        directiveDAO.save(directive1);
        Directive2 directive2 = new Directive2();
        directiveDAO.save(directive2);
    }

    private void createSpecialities() {
        List<Faculty> facultyList = facultyDAO.findAll();

        for (Faculty faculty : facultyList) {

            Speciality speciality = new Speciality();
            speciality.setName("Прикладная математика и информатика");
            speciality.setShortName("ПМИ");
            speciality.setCode("010501");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(5);
            specialityDAO.save(speciality);

            speciality = new Speciality();
            speciality.setName("Вычислительные машины, комплексы, системы, сети");
            speciality.setShortName("ВМ");
            speciality.setCode("230101");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(5);
            specialityDAO.save(speciality);

            speciality = new Speciality();
            speciality.setName("Компьютерная безопасность");
            speciality.setShortName("КБ");
            speciality.setCode("090");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(6);
            specialityDAO.save(speciality);

            speciality = new Speciality();
            speciality.setName("Информатика и вычислительная техника (направление)");
            speciality.setShortName("ИВТ");
            speciality.setCode("230100");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(4);
            specialityDAO.save(speciality);

            speciality = new Speciality();
            speciality.setName("Прикладная математика и информатика (направление)");
            speciality.setShortName("ПМИ (н)");
            speciality.setCode("010500");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(4);
            specialityDAO.save(speciality);
        }
    }

    private void createSpecialitiesAndGroups() {
        List<Faculty> facultyList = facultyDAO.findAll();

        for (Faculty faculty : facultyList) {

            Speciality speciality = new Speciality();
            speciality.setName("Прикладная математика и информатика");
            speciality.setShortName("ПМИ");
            speciality.setCode("010501");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(5);
            specialityDAO.save(speciality);

            Group group = new Group();
            group.setName("211");
            group.setCourse(2);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("311");
            group.setCourse(3);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("411");
            group.setCourse(4);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("511");
            group.setCourse(5);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            speciality = new Speciality();
            speciality.setName("Вычислительные машины, комплексы, системы, сети");
            speciality.setShortName("ВМ");
            speciality.setCode("230101");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(5);
            specialityDAO.save(speciality);

            group = new Group();
            group.setName("221");
            group.setCourse(2);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("222");
            group.setCourse(2);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("321");
            group.setCourse(3);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("421");
            group.setCourse(4);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("521");
            group.setCourse(5);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("522");
            group.setCourse(5);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            speciality = new Speciality();
            speciality.setName("Компьютерная безопасность");
            speciality.setShortName("КБ");
            speciality.setCode("090");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(6);
            specialityDAO.save(speciality);

            group = new Group();
            group.setName("131");
            group.setCourse(1);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("132");
            group.setCourse(1);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("231");
            group.setCourse(2);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("232");
            group.setCourse(2);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("331");
            group.setCourse(3);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("332");
            group.setCourse(3);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("431");
            group.setCourse(4);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("531");
            group.setCourse(5);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("631");
            group.setCourse(6);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("632");
            group.setCourse(6);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            speciality = new Speciality();
            speciality.setName("Информатика и вычислительная техника (направление)");
            speciality.setShortName("ИВТ");
            speciality.setCode("230100");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(4);
            specialityDAO.save(speciality);

            group = new Group();
            group.setName("251");
            group.setCourse(2);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("351");
            group.setCourse(2);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            speciality = new Speciality();
            speciality.setName("Прикладная математика и информатика (направление)");
            speciality.setShortName("ПМИ (н)");
            speciality.setCode("010500");
            speciality.setFaculty(faculty);
            speciality.setCourseCount(4);
            specialityDAO.save(speciality);

            group = new Group();
            group.setName("341");
            group.setCourse(3);
            group.setSpeciality(speciality);
            groupDAO.save(group);

            group = new Group();
            group.setName("441");
            group.setCourse(4);
            group.setSpeciality(speciality);
            groupDAO.save(group);
        }
    }

    private void createGroups() {
        List<Speciality> specialityList = specialityDAO.findAll();
        for (Speciality speciality : specialityList) {
            int courseCount = speciality.getCourseCount();
            for (int groupCount = 1; groupCount <= 2; ++groupCount) {
                for (int course = 1; course <= courseCount; ++course) {
                    Group group = new Group();
                    group.setName(course + speciality.getCode() + groupCount);
                    group.setCourse(course);
                    group.setSpeciality(speciality);
                    groupDAO.save(group);
                }
            }
        }
    }

    private void createStudents() {
        List<Group> groupList = groupDAO.findAll();

        List<EnrollmentOrder> orders = new ArrayList<EnrollmentOrder>();

        Integer courseCount = 0;

        for (Speciality speciality : specialityDAO.findAll()) {
            if (speciality.getCourseCount() > courseCount) {
                courseCount = speciality.getCourseCount();
            }
        }

        for (int i = 0; i < courseCount; ++i) {
            EnrollmentOrder enrollmentOrder = StudentGenerator.getRandomEnrollmentOrder(i + 1);
            orders.add(enrollmentOrder);
            enrollmentOrderDAO.save(enrollmentOrder);
        }

        for (Group group : groupList) {
            for (int studentCount = 1; studentCount <= COUNT_STUDENTS_IN_GROUP; ++studentCount) {
                Student student = StudentGenerator.getRandomStudent();

                student.setCourse(group.getCourse());
                student.setGroup(group);
                student.setSpeciality(group.getSpeciality());
                student.setEnrollmentOrder(orders.get(group.getCourse() - 1));

                studentDAO.save(student);
            }
        }

    }

    private static class StudentGenerator {
        private static Random generator = new Random(new Date().getTime());

        private static String[][] lastNames = {{"Иванов", "Петров", "Захаров", "Клочко", "Кузнецов", "Алексеев", "Свиридов",
                "Яковлев", "Нечаев", "Куприн", "Снегов", "Дубровский", "Шефер", "Привалов", "Горбовский", "Печкин"},
                {"Иванова", "Петрова", "Захарова", "Клочко", "Кузнецова", "Алексеева", "Свиридова",
                        "Яковлева", "Нечаева", "Куприна", "Снегова", "Дубровская", "Шефер", "Привалова", "Горбовская", "Печкина"}};
        private static String[][] lastNamesDative = {{"Иванову", "Петрову", "Захарову", "Клочко", "Кузнецову", "Алексееву", "Свиридову",
                "Яковлеву", "Нечаеву", "Куприну", "Снегову", "Дубровскому", "Шефер", "Привалову", "Горбовскому", "Печкину"},
                {"Ивановой", "Петровой", "Захаровой", "Клочко", "Кузнецовой", "Алексеевой", "Свиридовой",
                        "Яковлевой", "Нечаевой", "Куприной", "Снеговой", "Дубровской", "Шефер", "Приваловой", "Горбовской", "Печкиной"}};

        private static String[][] firstNames = {{"Иван", "Пётр", "Александр", "Семён", "Николай", "Алексей", "Юрий",
                "Борис", "Сергей", "Андрей", "Олег", "Степан", "Максим", "Дмитрий", "Владимир", "Виктор", "Денис"},
                {"Светлана", "Екатерина", "Александра", "Надежда", "Анна", "Юлия", "Томара", "Наталия",
                        "Елена", "Мария", "Марина", "Ольга", "Зульфия", "Алёна", "Лидия", "Антонина", "Анастасия", "Виктория"}};
        private static String[][] firstNamesDative = {{"Ивану", "Петру", "Александру", "Семёну", "Николаю", "Алексею", "Юрию",
                "Борису", "Сергею", "Андрею", "Олегу", "Степану", "Максиму", "Дмитрию", "Владимиру", "Виктору", "Денису"},
                {"Светлане", "Екатерине", "Александре", "Надежде", "Анне", "Юлии", "Томаре", "Наталии",
                        "Елене", "Марии", "Марине", "Ольге", "Зульфие", "Алёне", "Лидии", "Антонине", "Анастасии", "Виктории"}};

        private static String[][] middleNames = {{"Иванович", "Петрович", "Александрович", "Семёнович", "Николаевич", "Алексеевич",
                "Юрьевич", "Борисович", "Сергеевич", "Андреевич", "Олегович", "Степанович", "Максимович", "Дмитриевич",
                "Владимирович", "Викторович", "Денисович"},
                {"Ивановна", "Петровна", "Александровна", "Семёновна", "Николаевна", "Алексеевна",
                        "Юрьевна", "Борисовна", "Сергеевна", "Андреевна", "Олеговна", "Степановна", "Максимовна", "Дмитриевна",
                        "Владимировна", "Викторовна", "Денисовна"}};
        private static String[][] middleNamesDative = {{"Ивановичу", "Петровичу", "Александровичу", "Семёновичу", "Николаевичу", "Алексеевичу",
                "Юрьевичу", "Борисовичу", "Сергеевичу", "Андреевичу", "Олеговичу", "Степановичу", "Максимовичу", "Дмитриевичу",
                "Владимировичу", "Викторовичу", "Денисовичу"},
                {"Ивановне", "Петровне", "Александровне", "Семёновне", "Николаевне", "Алексеевне",
                        "Юрьевне", "Борисовне", "Сергеевне", "Андреевне", "Олеговне", "Степановне", "Максимовне", "Дмитриевне",
                        "Владимировне", "Викторовне", "Денисовне"}};

        public StudentGenerator() {
        }

        public static EnrollmentOrder getRandomEnrollmentOrder(int course) {
            EnrollmentOrder enrolOrder = new EnrollmentOrder();

            enrolOrder.setNumber("22-" + generator.nextInt(10) + generator.nextInt(10)
                    + generator.nextInt(10) + generator.nextInt(10));
            enrolOrder.setSignedDate(new GregorianCalendar().getTime());
            enrolOrder.setEnrollmentDate(new GregorianCalendar(2011 - course, Calendar.SEPTEMBER, 1).getTime());
            enrolOrder.setReleaseDate(new GregorianCalendar(2016 - course, Calendar.JULY, 1).getTime());

            return enrolOrder;
        }

        public static Student getRandomStudent() {
            Student student = new Student();
            int sex = generator.nextInt(2);
            Integer nameIndex = generator.nextInt(firstNames[sex].length);

            student.setSex(sex == 0 ? Person.Sex.MALE : Person.Sex.FEMALE);
            student.setFirstName(firstNames[sex][nameIndex]);
            student.setFirstNameDative(firstNamesDative[sex][nameIndex]);

            nameIndex = generator.nextInt(lastNames[sex].length);
            student.setLastName(lastNames[sex][nameIndex]);
            student.setLastNameDative(lastNamesDative[sex][nameIndex]);

            nameIndex = generator.nextInt(middleNames[sex].length);
            student.setMiddleName(middleNames[sex][nameIndex]);
            student.setMiddleNameDative(middleNamesDative[sex][nameIndex]);

            student.setStudyForm(getRandomStudyForm());
            student.setStudentIdNumber("" + ((int) (Math.random() * 100000)));
            student.setDivision(Student.Division.INTRAMURAL);

            student.setBirthday(new GregorianCalendar().getTime());

            student.setAdditionalData(getRandomAdditionalData(student));
//            additionalDataDAO.save(student.getAdditionalData());

            return student;
        }

        private static Student.StudyForm getRandomStudyForm() {
            switch (generator.nextInt(3)) {
                case 1:
                    return Student.StudyForm.COMMERCIAL;
            }
            return Student.StudyForm.BUDGET;
        }

        public static Student.AdditionalStudentData getRandomAdditionalData(Student student) {
            Student.AdditionalStudentData additionalData = new Student.AdditionalStudentData();
            Passport passport = new Passport(student);
            String address = "Российская Федерация, г. Саратов, ул. Астраханская, д. 77, кв. 13";

            additionalData.setBirthPlace("г. Саратов");
            additionalData.setEducation("Средняя школа");
            additionalData.setWorkInfo("Грузчик");

            passport.setActual(true);
            passport.setSeries("63 04");
            passport.setNumber("" + generator.nextInt(10) + generator.nextInt(10) + generator.nextInt(10)
                    + generator.nextInt(10) + generator.nextInt(10) + generator.nextInt(10));
            passport.setCitizenship("РФ");
            passport.setIssuedDate(new GregorianCalendar().getTime());
            passport.setIssuingOrganization("УВД Заводского р-на г. Саратова");
            passport.setAddress(address);
//            passportDAO.save(passport);
            additionalData.addPassport(passport);

            additionalData.setActualAddress(address);
            additionalData.setOldAddress(address);
            additionalData.setFather(getRandomParent(Person.Sex.MALE));
            additionalData.setMother(getRandomParent(Person.Sex.FEMALE));
            additionalData.getFather().setAddress(address);
            additionalData.getMother().setAddress(address);
            parentDAO.save(additionalData.getFather());
            parentDAO.save(additionalData.getMother());

            PhotoService photoService = new PhotoServiceImpl();
            Photo photo;
            try {
                photo = photoService.loadFromFile("C:/temp/photo.jpg");
            } catch (IOException e) {
                throw new RuntimeException("Photo not found!!!", e);
            }

            photoDAO.save(photo);
            additionalData.setPhoto(photo);

            additionalData.setMaritalStatus("Всё сложно... :/");
            additionalData.setChildrenInfo("Нет");

            return additionalData;
        }

        public static Parent getRandomParent(Person.Sex sexValue) {
            Parent parent = new Parent();

            int sex = sexValue == Person.Sex.MALE ? 0 : 1;
            Integer nameIndex = generator.nextInt(firstNames[sex].length);

            parent.setSex(sex == 0 ? Person.Sex.MALE : Person.Sex.FEMALE);
            parent.setFirstName(firstNames[sex][nameIndex]);
            parent.setFirstNameDative(firstNamesDative[sex][nameIndex]);

            nameIndex = generator.nextInt(lastNames[sex].length);
            parent.setLastName(lastNames[sex][nameIndex]);
            parent.setLastNameDative(lastNamesDative[sex][nameIndex]);

            nameIndex = generator.nextInt(middleNames[sex].length);
            parent.setMiddleName(middleNames[sex][nameIndex]);
            parent.setMiddleNameDative(middleNamesDative[sex][nameIndex]);

            parent.setBirthday(new GregorianCalendar().getTime());
            parent.setPhoneNumbers("02, 03, 89875858888");
            parent.setWorkInfo("ООО \"Машиностроитель\", системный программист");

            return parent;
        }
    }
}
