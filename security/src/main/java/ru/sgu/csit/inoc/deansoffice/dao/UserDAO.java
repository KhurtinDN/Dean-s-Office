package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.User;

/**
 * This service allow access to user entity.
 *
 * @author Denis Khurtin
 */
public interface UserDAO {
    User findByUsername(String username);
    void saveOrUpdate(User user);
    void delete(User user);
}
