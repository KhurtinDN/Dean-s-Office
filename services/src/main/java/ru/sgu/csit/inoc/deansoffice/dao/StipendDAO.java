package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Stipend;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Mesheryakov
 */
public interface StipendDAO extends BaseDAO<Stipend, Long> {
    List<Stipend> findByAllParam(Stipend.StipendType type, Date startDate, Date endDate, Integer value);
}
