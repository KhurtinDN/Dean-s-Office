package ru.sgu.csit.inoc.deansoffice.dao.impl;

import com.trg.dao.hibernate.GenericDAOImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 3, 2010
 * Time: 1:06:29 PM
 */
public class BaseDAO<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {
    @Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
