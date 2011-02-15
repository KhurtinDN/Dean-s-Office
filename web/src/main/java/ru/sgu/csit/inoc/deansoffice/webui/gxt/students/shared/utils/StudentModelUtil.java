package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 2:48 PM
 */
public class StudentModelUtil extends PersonModelUtil {
    public static String getFullName(StudentModel studentModel) {
        return studentModel.getLastName() + ' ' + studentModel.getFirstName() + ' ' + studentModel.getMiddleName();
    }

    public static String divisionToString(StudentModel.Division division) {
        if (division == null) {
            return "";
        }
        switch (division) {
            case INTRAMURAL:
                return "Дневное";
            case EXTRAMURAL:
                return "Заочное";
            case EVENINGSTUDY:
                return "Вечернее";
            default:
                return "";
        }
    }

    public static String studyFormToString(StudentModel.StudyForm studyForm) {
        if (studyForm == null) {
            return "";
        }
        switch (studyForm) {
            case BUDGET:
                return "Бюджетная";
            case COMMERCIAL:
                return "Коммерческая";
            default:
                return "";
        }
    }
}
