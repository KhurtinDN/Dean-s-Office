package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.StipendDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Stipend;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class StipendDAOImpl extends BaseDAOImpl<Stipend, Long> implements StipendDAO {

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Stipend> findByAllParam(Stipend.StipendType type, Date startDate, Date endDate, Integer value) {
        return getHibernateTemplate().find("from Stipend s where s.type=? and s.startDate=? and s.endDate=? and s.value=?",
                type, startDate, endDate, value);
    }
}
