package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.UserDAO;
import ru.sgu.csit.inoc.deansoffice.domain.User;

/**
 * @author Denis Khurtin
 */
@Service
public class UserProvider implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, DataAccessException {
        final User user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with name %s not found", username));
        }

        return user;
    }
}
