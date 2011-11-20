package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.AdministrationDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Administration;

import java.util.List;

/**
 * The DAO for administration entities.
 *
 * @author Alexander Mesheryakov, Denis Khurtin
 */
@Repository
public class AdministrationDAOImpl extends BaseDAOImpl<Administration, Long> implements AdministrationDAO {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Administration load() {
        final List<Administration> administrationList = findAll();

        Administration administration;

        if (administrationList.isEmpty()) {
            administration = new Administration();
            save(administration);
        } else if (administrationList.size() == 1) {
            administration = administrationList.get(0);
        } else {
            throw new IllegalStateException("The administration must be unique.");
        }

        return administration;
    }
}
