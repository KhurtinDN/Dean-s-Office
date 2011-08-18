package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Administration;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.AdministrationModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;

/**
 * @author Denis Khurtin
 */
public class AdministrationUtil {
    public static AdministrationModel convertAdministrationModelToAdministration(final Administration administration) {
        final AdministrationModel administrationModel = new AdministrationModel();
        administrationModel.setName(administration.getName());

        final EmployeeModel rectorModel = EmployeeUtil.convertEmployeeToEmployeeModel(administration.getRector());
        administrationModel.setRector(rectorModel);

        return administrationModel;
    }
}
