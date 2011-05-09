package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.util;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PersonModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 2:50 PM
 */
public class PersonModelUtil {
    public static String sexToString(PersonModel.Sex sex) {
        if (sex == null) {
            return "";
        }
        switch (sex) {
            case MALE:
                return "Мужской";
            case FEMALE:
                return "Женский";
            default:
                return "";
        }
    }
}
