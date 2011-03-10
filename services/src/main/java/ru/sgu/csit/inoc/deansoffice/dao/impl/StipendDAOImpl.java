package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.StipendDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Stipend;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 04.03.11
 * Time: 11:09
 */
@Repository
public class StipendDAOImpl extends BaseDAOImpl<Stipend, Long> implements StipendDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<Stipend> findByAllParam(Stipend.StipendType type, Date startDate, Date endDate, Integer value) {
//        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("type", type),
//                Restrictions.eq("startDate", startDate), Restrictions.eq("endDate", endDate),
//                Restrictions.eq("value", value));
//        return (List<Stipend>) getHibernateTemplate().findByCriteria(criteria);
        return (List<Stipend>) getHibernateTemplate().find("from Stipend s where s.type=? and s.startDate=? and s.endDate=? and s.value=?",
                type, startDate, endDate, value);
    }
}
