package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.EmployeeDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Employee;

import java.util.List;

/**
 * @author Denis Khurtin
 */
@Repository
public class EmployeeDAOImpl extends BaseDAOImpl<Employee, Long> implements EmployeeDAO {

    @Transactional(readOnly = true)
    @Override
    public Employee findDeanById(Long id) {
        return findById(id); // todo: need return dean only
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAllDeans() {
        return findAll(); // todo: need return deans only
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAllRectors() {
        return findAll(); // todo: need return rectors only
    }
}
