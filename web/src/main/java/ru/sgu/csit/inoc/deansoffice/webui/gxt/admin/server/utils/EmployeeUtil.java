package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Employee;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util.PersonUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Khurtin
 */
public class EmployeeUtil extends PersonUtil {
    public static EmployeeModel convertEmployeeToEmployeeModel(final Employee employee) {
        if (employee == null) {
            return null;
        }

        final EmployeeModel employeeModel = new EmployeeModel();
        populatePersonModelByPerson(employeeModel, employee);
        employeeModel.setDegree(employee.getDegree());
        employeeModel.setPosition(employee.getPosition());

        return employeeModel;
    }

    public static List<EmployeeModel> convertEmployeeListToEmployeeModelList(final List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        final List<EmployeeModel> employeeModelList = new ArrayList<EmployeeModel>(employees.size());

        for (Employee employee : employees) {
            final EmployeeModel employeeModel = convertEmployeeToEmployeeModel(employee);
            employeeModelList.add(employeeModel);
        }

        return employeeModelList;
    }

    public static void populateEmployeeByEmployeeModel(final Employee employee, final EmployeeModel employeeModel) {
        populatePersonByPersonModel(employee, employeeModel);
        employee.setDegree(employeeModel.getDegree());
        employee.setPosition(employeeModel.getPosition());
    }
}
