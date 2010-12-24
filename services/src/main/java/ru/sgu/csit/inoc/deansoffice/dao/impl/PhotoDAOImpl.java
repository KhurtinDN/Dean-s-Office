package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.PhotoDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Photo;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 24.12.10
 * Time: 9:30
 */
@Repository
public class PhotoDAOImpl extends BaseDAOImpl<Photo, Long> implements PhotoDAO {
}
