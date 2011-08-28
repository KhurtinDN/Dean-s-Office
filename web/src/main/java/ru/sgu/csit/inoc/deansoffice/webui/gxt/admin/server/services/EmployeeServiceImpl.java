package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.EmployeeDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Employee;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.EmployeeService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.EmployeeUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;

import java.util.List;

/**
 * @author Denis Khurtin
 */
@Service("AdminStaffService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<EmployeeModel> loadStaffList() {
        final List<Employee> employeeList = employeeDAO.findAll();

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
    public EmployeeModel saveOrUpdate(EmployeeModel employeeModel) {
        Validate.notNull(employeeModel, "employeeModel is null");

        final Employee employee = new Employee();

        EmployeeUtil.populateEmployeeByEmployeeModel(employee, employeeModel);

        employeeDAO.saveOrUpdate(employee);

        return EmployeeUtil.convertEmployeeToEmployeeModel(employee);
    }

    @Override
    public void delete(List<Long> employeeIdList) {
        Validate.noNullElements(employeeIdList, "EmployeeId list is null or contains null element.");

        for (final Long id : employeeIdList) {
            employeeDAO.deleteById(id);
        }
    }
}
