package ru.sgu.csit.inoc.deansoffice.database;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 11:09:18 AM
 */
public class SessionFactoryProvider {
    private static final SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(SessionFactoryProvider.class);

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable e) {
            LOGGER.error("The session factory can't be created:", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
