package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.AdditionalStudentDataDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class AdditionalStudentDataDAOImpl extends BaseDAOImpl<Student.AdditionalStudentData, Long> implements AdditionalStudentDataDAO {
}
