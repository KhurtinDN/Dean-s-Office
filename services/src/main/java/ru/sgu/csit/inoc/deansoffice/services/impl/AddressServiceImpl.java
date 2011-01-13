package ru.sgu.csit.inoc.deansoffice.services.impl;

import ru.sgu.csit.inoc.deansoffice.domain.Address;
import ru.sgu.csit.inoc.deansoffice.services.AddressService;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 12:36
 */
public class AddressServiceImpl implements AddressService {
    @Override
    public String toSimpleAddressString(Address address) {
        String region = address.getRegion();
        String district = address.getDistrict();
        String town = address.getTown();
        String streetAddress = address.getStreetAddress();

        return (region == null ? "" : region + ", ") + (district == null ? "" : district + ", ") +
                (town == null ? "" : town + ", ") + (streetAddress == null ? "" : streetAddress);
    }
}
