package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.DeanDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Dean;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util.PersonUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PersonModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StaffService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 11:40 AM
 */
@Service("AdminStaffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    private DeanDAO deanDAO;

    @Override
    public List<PersonModel> loadStaffList() {
        return new ArrayList<PersonModel>();  // todo: implement
    }

    @Override
    public List<PersonModel> loadDeanList() {
        List<Dean> deanList = deanDAO.findAll();
        List<PersonModel> deanModelList = new ArrayList<PersonModel>(deanList.size());

        for (Dean dean : deanList) {
            PersonModel deanModel = new PersonModel();
            PersonUtil.populatePersonModelByPerson(deanModel, dean);
            deanModelList.add(deanModel);
        }

        return deanModelList;
    }
}
