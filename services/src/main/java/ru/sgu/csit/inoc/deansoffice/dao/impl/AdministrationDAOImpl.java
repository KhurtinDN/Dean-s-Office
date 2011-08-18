package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.AdministrationDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Administration;

import java.util.List;

/**
 * @author Denis Khurtin
 */
@Repository
public class AdministrationDAOImpl extends BaseDAOImpl<Administration, Long> implements AdministrationDAO {
    @Override
    public Administration load() {
        List<Administration> administrationList = findAll();

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
