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
            case REFERENCE1:
                return "Справка #1";
            case REFERENCE2:
                return "Справка #2";
            case REFERENCE3:
                return "Справка #3";
            default:
                return "";
        }
    }

    public static String statusToString(ReferenceModel.Status status) {
        if (status == null) {
            return "";
        }
        switch (status) {
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
