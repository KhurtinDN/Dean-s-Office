package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 11:08 AM
 */
public class ReferenceModelUtil {
    public static String typeToString(ReferenceModel.ReferenceType type) {
        if (type == null) {
            return "";
        }
        switch (type) {
            case REFERENCE_1:
                return "Справка #1";
            case REFERENCE_2:
                return "Справка #2";
            case REFERENCE_3:
                return "Справка #3";
            default:
                return "";
        }
    }

    public static String statusToString(ReferenceModel.ReferenceState referenceState) {
        if (referenceState == null) {
            return "";
        }
        switch (referenceState) {
            case REGISTERED:
                return "Зарегистрирована";
            case PROCESSED:
                return "Обрабатывается";
            case READY:
                return "Готова к выдаче";
            case ISSUED:
                return "Выдана";
            default:
                return "";
        }
    }
}
