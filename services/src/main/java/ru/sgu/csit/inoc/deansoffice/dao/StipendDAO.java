package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Stipend;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 04.03.11
 * Time: 11:07
 */
public interface StipendDAO extends BaseDAO<Stipend, Long> {
    List<Stipend> findByAllParam(Stipend.StipendType type, Date startDate, Date endDate, Integer value);
}
