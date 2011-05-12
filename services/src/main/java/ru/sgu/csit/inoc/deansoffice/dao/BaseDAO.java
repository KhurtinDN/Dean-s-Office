package ru.sgu.csit.inoc.deansoffice.dao;

import java.io.Serializable;
import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 11, 2010
 * Time: 10:36:13 AM
 */
public interface BaseDAO<T, ID extends Serializable> {
    ID save(T entity);
    void saveOrUpdate(T entity);
    void update(T transientObject);
    void delete(T persistentObject);
    boolean deleteById(ID id);
    T findById(ID id);
    List<T> findAll();
}
