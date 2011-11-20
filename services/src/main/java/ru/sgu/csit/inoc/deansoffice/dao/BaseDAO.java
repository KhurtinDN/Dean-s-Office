package ru.sgu.csit.inoc.deansoffice.dao;

import java.io.Serializable;
import java.util.List;

/**
 * The generic base DAO superclass for other DAO classes.
 *
 * @author Alexander Mesheryakov, Denis Khurtin
 */
public interface BaseDAO<T, ID extends Serializable> {
    T findById(ID id);
    List<T> findAll();
    ID save(T entity);
    void saveOrUpdate(T entity);
    void update(T transientObject);
    void delete(T persistentObject);
    boolean deleteById(ID id);
}
