package ru.sgu.csit.inoc.deansoffice.dao;

import java.io.Serializable;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 11, 2010
 * Time: 10:36:13 AM
 */
public interface BaseDAO<T, ID extends Serializable> {
    void save(T entity);
}
