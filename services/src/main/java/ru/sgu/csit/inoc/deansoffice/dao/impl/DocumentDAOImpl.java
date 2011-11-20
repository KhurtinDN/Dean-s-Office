package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.DocumentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Document;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class DocumentDAOImpl extends BaseDAOImpl<Document, Long> implements DocumentDAO {
}
