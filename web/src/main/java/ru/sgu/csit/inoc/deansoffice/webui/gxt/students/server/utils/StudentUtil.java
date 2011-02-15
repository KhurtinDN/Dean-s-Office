package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 3:13 PM
 */
public class StudentUtil extends PersonUtil {
    public static List<StudentModel> convertStudentsToStudentModels(List<Student> studentList) {
        List<StudentModel> studentModelList = new ArrayList<StudentModel>(studentList.size());

        Group group = new Group();
        Speciality speciality = new Speciality();
        GroupModel groupModel = null;
        SpecialityModel specialityModel = null;

        for (Student student : studentList) {
            StudentModel studentModel = new StudentModel();
            populatePerson(student, studentModel);

            studentModel.setStudentIdNumber(student.getStudentIdNumber());
            studentModel.setDivision( convertStudentDivisionToStudentModelDivision(student.getDivision()));
            studentModel.setStudyForm(convertStudentStudyFormToStudentModelStudyForm(student.getStudyForm()));

            if (!group.equals(student.getGroup())) {
                group = student.getGroup();
                groupModel = GroupUtil.convertGroupToGroupModel(group);
            }
            if (!speciality.equals(group.getSpeciality())) {
                speciality = group.getSpeciality();
                specialityModel = SpecialityUtil.convertSpecialityToSpecialityModel(speciality);

                assert groupModel != null;
                groupModel.setSpeciality(specialityModel);
            }

            studentModel.setGroup(groupModel);
            studentModel.setSpeciality(specialityModel);
            studentModel.setCourse(student.getCourse());

            if (student.getAdditionalData() != null && student.getAdditionalData().getPhoto() != null) {
                studentModel.setPhotoId(student.getAdditionalData().getPhoto().getId());
            }

            studentModelList.add(studentModel);
        }

        return studentModelList;
    }

    public static StudentDetailsModel convertStudentToStudentDetailsModel(Student student) {
        StudentDetailsModel studentDetailsModel = new StudentDetailsModel();
        populatePerson(student, studentDetailsModel);

        studentDetailsModel.setStudentIdNumber(student.getStudentIdNumber());
        studentDetailsModel.setDivision(convertStudentDivisionToStudentModelDivision(student.getDivision()));
        studentDetailsModel.setStudyForm(convertStudentStudyFormToStudentModelStudyForm(student.getStudyForm()));

        SpecialityModel specialityModel = SpecialityUtil.convertSpecialityToSpecialityModel(student.getSpeciality());
        studentDetailsModel.setSpeciality(specialityModel);
        studentDetailsModel.setGroup(GroupUtil.convertGroupToGroupModel(student.getGroup(), specialityModel));

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
            studentDetailsModel.setMother(ParentUtil.convertParentToParentModel(additionalStudentData.getMather()));

            studentDetailsModel.setOldAddress(additionalStudentData.getOldAddress());
            studentDetailsModel.setActualAddress(additionalStudentData.getActualAddress());

            if (additionalStudentData.getPhoto() != null) {
                studentDetailsModel.setPhotoId(additionalStudentData.getPhoto().getId());
            }
        }

        return studentDetailsModel;
    }

    private static StudentModel.Division convertStudentDivisionToStudentModelDivision(Student.Division division) {
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

    private static StudentModel.StudyForm convertStudentStudyFormToStudentModelStudyForm(Student.StudyForm studyForm) {
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

    public static Student convertStudentDetailsModelToStudent(StudentDetailsModel studentDetailsModel) {
        return null;  //ToDo
    }
}
