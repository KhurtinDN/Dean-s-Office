package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Authority;

import java.util.List;

/**
 * This service allow access to authority entity.
 *
 * @author Denis Khurtin
 */
public interface AuthorityDAO {
    List<Authority> loadAll();
    void save(Authority authority);
}
