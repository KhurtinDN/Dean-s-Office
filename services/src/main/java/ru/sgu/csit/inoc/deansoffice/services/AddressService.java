package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Address;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 12:35
 */
public interface AddressService {
    String toSimpleAddressString(Address address);
}
