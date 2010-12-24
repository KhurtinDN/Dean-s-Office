package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.AddressDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Address;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 24.12.10
 * Time: 9:25
 */
@Repository
public class AddressDAOImpl extends BaseDAOImpl<Address, Long> implements AddressDAO {
}
