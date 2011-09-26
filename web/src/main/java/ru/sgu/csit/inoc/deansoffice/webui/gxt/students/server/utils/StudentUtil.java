package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Parent;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util.PersonUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.ParentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentDetailsModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khurtin Denis
 */
public class StudentUtil extends PersonUtil {
    public static StudentModel convertStudentToStudentModel(Student student) {
        final StudentModel studentModel = new StudentModel();
        populatePersonModelByPerson(studentModel, student);

        studentModel.setStudentIdNumber(student.getStudentIdNumber());
        studentModel.setDivision( convertStudentDivisionToStudentModelDivision(student.getDivision()));
        studentModel.setStudyForm(convertStudentStudyFormToStudentModelStudyForm(student.getStudyForm()));

        studentModel.setGroupName(student.getGroup().getName());
        studentModel.setSpecialityName(student.getSpeciality().getName());
        studentModel.setCourse(student.getGroup().getCourse());

        if (student.getAdditionalData() != null && student.getAdditionalData().getPhoto() != null) {
            studentModel.setPhotoId(student.getAdditionalData().getPhoto().getId());
        }

        return studentModel;
    }

    public static List<StudentModel> convertStudentsToStudentModels(List<Student> studentList) {
        if (studentList == null) {
            return null;
        }

        final List<StudentModel> studentModelList = new ArrayList<StudentModel>(studentList.size());

        for (Student student : studentList) {
            final StudentModel studentModel = convertStudentToStudentModel(student);
            studentModelList.add(studentModel);
        }

        return studentModelList;
    }

    public static StudentDetailsModel convertStudentToStudentDetailsModel(Student student) {
        if (student == null) {
            return null;
        }

        StudentDetailsModel studentDetailsModel = new StudentDetailsModel();
        populatePersonModelByPerson(studentDetailsModel, student);

        studentDetailsModel.setStudentIdNumber(student.getStudentIdNumber());
        studentDetailsModel.setDivision(convertStudentDivisionToStudentModelDivision(student.getDivision()));
        studentDetailsModel.setStudyForm(convertStudentStudyFormToStudentModelStudyForm(student.getStudyForm()));

        SpecialityModel specialityModel = SpecialityUtil.convertSpecialityToSpecialityModel(student.getSpeciality());
        studentDetailsModel.setSpeciality(specialityModel);
        studentDetailsModel.setGroup(GroupUtil.convertGroupToGroupModel(student.getGroup(), specialityModel));

        studentDetailsModel.setSpecialityName(student.getSpeciality().getName());
        studentDetailsModel.setGroupName(student.getGroup().getName());
        studentDetailsModel.setCourse(student.getGroup().getCourse());

        if (student.getAdditionalData() != null) {
            Student.AdditionalStudentData additionalStudentData = student.getAdditionalData();

            studentDetailsModel.setBirthplace(additionalStudentData.getBirthPlace());
            studentDetailsModel.setEducation(additionalStudentData.getEducation());
            studentDetailsModel.setWorkInfo(additionalStudentData.getWorkInfo());

            studentDetailsModel.setPassports(
                    PassportUtil.convertPassportListsToPassportModelList(additionalStudentData.getPassports()));

            studentDetailsModel.setMaritalStatus(additionalStudentData.getMaritalStatus());
            studentDetailsModel.setChildrenInfo(additionalStudentData.getChildrenInfo());

            studentDetailsModel.setFather(ParentUtil.convertParentToParentModel(additionalStudentData.getFather()));
            studentDetailsModel.setMother(ParentUtil.convertParentToParentModel(additionalStudentData.getMother()));

            studentDetailsModel.setOldAddress(additionalStudentData.getOldAddress());
            studentDetailsModel.setAddress(additionalStudentData.getActualAddress());

            if (additionalStudentData.getPhoto() != null) {
                studentDetailsModel.setPhotoId(additionalStudentData.getPhoto().getId());
            }
        }

        return studentDetailsModel;
    }

    public static StudentModel.Division convertStudentDivisionToStudentModelDivision(Student.Division division) {
        if (division == null) {
            return null;
        }

        switch (division) {
            case INTRAMURAL:
                return StudentModel.Division.INTRAMURAL;
            case EXTRAMURAL:
                return StudentModel.Division.EXTRAMURAL;
            case EVENINGSTUDY:
                return StudentModel.Division.EVENINGSTUDY;
            default:
                return null;
        }
    }


    public static StudentModel.StudyForm convertStudentStudyFormToStudentModelStudyForm(Student.StudyForm studyForm) {
        if (studyForm == null) {
            return null;
        }

        switch (studyForm) {
            case BUDGET:
                return StudentModel.StudyForm.BUDGET;
            case COMMERCIAL:
                return StudentModel.StudyForm.COMMERCIAL;
            default:
                return null;
        }
    }

    public static void populateStudentByStudentDetailsModel(Student student, StudentDetailsModel studentDetailsModel) {
        if (student == null || studentDetailsModel == null) {
            throw new IllegalArgumentException("Arguments must be not null.");
        }

        student.setBirthday(studentDetailsModel.getBirthday());
        student.setSex(convertPersonModelSexToPersonSex(studentDetailsModel.getSex()));

        Student.AdditionalStudentData additionalStudentData = student.getAdditionalData();
        if (additionalStudentData == null) {
            additionalStudentData = new Student.AdditionalStudentData();
        }
        additionalStudentData.setBirthPlace(studentDetailsModel.getBirthplace());
        additionalStudentData.setEducation(studentDetailsModel.getEducation());
        additionalStudentData.setWorkInfo(studentDetailsModel.getWorkInfo());
        additionalStudentData.setMaritalStatus(studentDetailsModel.getMaritalStatus());
        additionalStudentData.setChildrenInfo(studentDetailsModel.getChildrenInfo());


        additionalStudentData.setPassports(
                PassportUtil.convertPassportModelListsToPassportList(studentDetailsModel.getPassports()));

        additionalStudentData.setOldAddress(studentDetailsModel.getOldAddress());
        additionalStudentData.setActualAddress(studentDetailsModel.getAddress());

        if (studentDetailsModel.getFather() == null) {
            additionalStudentData.setFather(null);
        } else {
            ParentModel fatherModel = studentDetailsModel.getFather();

            Parent father = additionalStudentData.getFather();
            if (father == null) {
                father = new Parent();
            }
            ParentUtil.populateParentByParentModel(father, fatherModel);

            additionalStudentData.setFather(father);
        }

        if (studentDetailsModel.getMother() == null) {
            additionalStudentData.setMother(null);
        } else {
            ParentModel motherModel = studentDetailsModel.getMother();

            Parent mother = additionalStudentData.getMother();
            if (mother == null) {
                mother = new Parent();
            }
            ParentUtil.populateParentByParentModel(mother, motherModel);

            additionalStudentData.setMother(mother);
        }
    }

    public static Student.Division convertStudentModelDivisionToStudentDivision(StudentModel.Division division) {
        if (division == null) {
            return null;
        }

        switch (division) {
            case INTRAMURAL:
                return Student.Division.INTRAMURAL;
            case EXTRAMURAL:
                return Student.Division.EXTRAMURAL;
            case EVENINGSTUDY:
                return Student.Division.EVENINGSTUDY;
            default:
                return null;
        }
    }

    public static Student.StudyForm convertStudentModelStudyFormStudentStudyForm(StudentModel.StudyForm studyForm) {
        if (studyForm == null) {
            return null;
        }

        switch (studyForm) {
            case BUDGET:
                return Student.StudyForm.BUDGET;
            case COMMERCIAL:
                return Student.StudyForm.COMMERCIAL;
            default:
                return null;
        }
    }
}
