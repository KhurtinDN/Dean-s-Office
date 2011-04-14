package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Parent;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.ParentModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 3:45 PM
 */
public class ParentUtil extends PersonUtil {
    public static void populateParentByParentModel(Parent parent, ParentModel parentModel) {
        if (parent == null || parentModel == null) {
            throw new IllegalArgumentException("Argument must be not null");
        }

        populatePersonByPersonModel(parent, parentModel);

        parent.setPhoneNumbers(parentModel.getPhoneNumbers());
        parent.setWorkInfo(parentModel.getWorkInfo());
        parent.setAddress(parentModel.getAddress());
    }

    public static ParentModel convertParentToParentModel(Parent parent) {
        if (parent == null) {
            return null;
        }

        ParentModel parentModel = new ParentModel();
        populatePersonModelByPerson(parentModel, parent);

        parentModel.setPhoneNumbers(parent.getPhoneNumbers());
        parentModel.setWorkInfo(parent.getWorkInfo());
        parentModel.setAddress(parent.getAddress());

        return parentModel;
    }
}
