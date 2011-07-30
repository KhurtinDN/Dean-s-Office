package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Employee;

import java.util.List;

/**
 * @author Denis Khurtin
 */
public interface EmployeeDAO extends BaseDAO<Employee, Long> {
    Employee findDeanById(Long id);
    List<Employee> findAllDeans();
}
