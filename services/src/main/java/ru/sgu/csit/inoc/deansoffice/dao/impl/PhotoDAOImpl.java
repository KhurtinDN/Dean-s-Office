package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.PhotoDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Photo;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class PhotoDAOImpl extends BaseDAOImpl<Photo, Long> implements PhotoDAO {
}
