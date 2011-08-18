package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.AdministrationDAO;
import ru.sgu.csit.inoc.deansoffice.dao.EmployeeDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Administration;
import ru.sgu.csit.inoc.deansoffice.domain.Employee;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.AdministrationService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.AdministrationUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.AdministrationModel;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 11:40 AM
 */
@Service("AdminAdministrationService")
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    private AdministrationDAO administrationDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public AdministrationModel load() {
        final Administration administration = administrationDAO.load();

        return AdministrationUtil.convertAdministrationModelToAdministration(administration);
    }

    @Override
    public void update(AdministrationModel administrationModel) {
        Validate.notNull(administrationModel, "administrationModel is null");
        Validate.notNull(administrationModel.getId(), "administrationModelId is null");
        Validate.notNull(administrationModel.getRector(), "rectorModel is null");
        Validate.notNull(administrationModel.getRector().getId(), "rectorModelId is null");

        final Administration administration = administrationDAO.findById(administrationModel.getId());
        final Employee rector = employeeDAO.findById(administrationModel.getRector().getId());

        administration.setName(administrationModel.getName());
        administration.setRector(rector);

        administrationDAO.update(administration);
    }
}
