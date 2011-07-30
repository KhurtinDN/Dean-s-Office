package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.EmployeeDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Employee;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StaffService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util.PersonUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 11:40 AM
 */
@Service("AdminStaffService")
public class StaffServiceImpl implements StaffService {
    private List<PersonModel> staffList = Lists.newArrayList(); // todo: implement
    {
        PersonModel personModel = new PersonModel();
        personModel.setId((long) staffList.size());
        personModel.setFullName("Петров Петр Петрович");
        staffList.add(personModel);

        personModel = new PersonModel();
        personModel.setId((long) staffList.size());
        personModel.setFullName("Кузьма Кузьмич");
        staffList.add(personModel);
    }

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<PersonModel> loadStaffList() {
        return staffList;
    }

    @Override
    public List<PersonModel> loadDeanList() {
        List<Employee> deanList = employeeDAO.findAllDeans();
        List<PersonModel> deanModelList = new ArrayList<PersonModel>(deanList.size());

        for (Employee dean : deanList) {
            PersonModel deanModel = new PersonModel();
            PersonUtil.populatePersonModelByPerson(deanModel, dean);
            deanModelList.add(deanModel);
        }

        return deanModelList;
    }

    @Override
    public PersonModel create() {
        PersonModel personModel = new PersonModel();
        personModel.setId((long) staffList.size());
        staffList.add(personModel);

        return personModel;
    }

    @Override
    public void update(PersonModel personModel) {
        staffList.set(personModel.getId().intValue(), personModel);
    }

    @Override
    public void delete(List<Long> personIdList) {
        List<PersonModel> personModelList = new ArrayList<PersonModel>(personIdList.size());

        for (Long id : personIdList) {
            personModelList.add(staffList.get(id.intValue()));
        }

        staffList.removeAll(personModelList);
    }
}
