package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 10:35:52 AM
 */
@Repository
public class StudentDAOImpl extends BaseDAO<Student, Long> implements StudentDAO {
}
