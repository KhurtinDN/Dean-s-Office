package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.EmployeeDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Employee;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StaffService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.EmployeeUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 11:40 AM
 */
@Service("AdminStaffService")
public class StaffServiceImpl implements StaffService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<EmployeeModel> loadStaffList() {
        List<Employee> employeeList = employeeDAO.findAll();

        return EmployeeUtil.convertEmployeeListToEmployeeModelList(employeeList);
    }

    @Override
    public List<EmployeeModel> loadDeanList() {
        final List<Employee> deanList = employeeDAO.findAllDeans();

        return EmployeeUtil.convertEmployeeListToEmployeeModelList(deanList);
    }

    @Override
    public List<EmployeeModel> loadRectorList() {
        final List<Employee> rectorList = employeeDAO.findAllRectors();

        return EmployeeUtil.convertEmployeeListToEmployeeModelList(rectorList);
    }

    @Override
    public EmployeeModel create() {
        final Employee employee = new Employee();

        employeeDAO.save(employee);

        return EmployeeUtil.convertEmployeeToEmployeeModel(employee);
    }

    @Override
    public void update(EmployeeModel employeeModel) {
        Validate.notNull(employeeModel, "employeeModel is null");
        Validate.notNull(employeeModel.getId(), "employeeModelId is null");

        final Employee employee = employeeDAO.findById(employeeModel.getId());

        EmployeeUtil.populateEmployeeByEmployeeModel(employee, employeeModel);

        employeeDAO.update(employee);
    }

    @Override
    public void delete(List<Long> employeeIdList) {
        Validate.noNullElements(employeeIdList, "EmployeeId list is null or contains null element.");

        for (Long id : employeeIdList) {
            employeeDAO.deleteById(id);
        }
    }
}
