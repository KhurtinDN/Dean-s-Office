package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Parent;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ParentModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 3:45 PM
 */
public class ParentUtil extends PersonUtil {
    public static ParentModel convertParentToParentModel(Parent parent) {
        ParentModel parentModel = new ParentModel();
        populatePerson(parent, parentModel);

        parentModel.setPhoneNumbers(parent.getPhoneNumbers());
        parentModel.setWorkInfo(parent.getWorkInfo());
        parentModel.setAddress(parent.getAddress());

        return parentModel;
    }
}
