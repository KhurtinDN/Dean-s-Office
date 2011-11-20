package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;

import java.util.List;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
@Repository
public class FacultyDAOImpl extends BaseDAOImpl<Faculty, Long> implements FacultyDAO {

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Faculty> findByShortName(String shortName) {
        return getHibernateTemplate().find("from Faculty f where f.shortName=?", shortName);
    }
}
