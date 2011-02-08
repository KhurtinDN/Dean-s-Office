package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/8/11
 * Time: 10:57 AM
 */
public class AddressModel extends DtoModel {

    public String getPostalCode() { // Почтовый индекс или zip-код
        return get("postalCode");
    }

    public void setPostalCode(String postalCode) {
        set("postalCode", postalCode);
    }

    public String getCountry() { // Страна
        return get("country");
    }

    public void setCountry(String country) {
        set("country", country);
    }

    public String getRegion() { // Республика, край, область, автономный округ, штат
        return get("region");
    }

    public void setRegion(String region) {
        set("region", region);
    }

    public String getDistrict() { // Район
        return get("district");
    }

    public void setDistrict(String district) {
        set("district", district);
    }

    public String getTown() { // Населённый пункт
        return get("town");
    }

    public void setTown(String town) {
        set("town", town);
    }

    public String getStreetAddress() { // Улица, дом, квартира
        return get("streetAddress");
    }

    public void setStreetAddress(String streetAddress) {
        set("streetAddress", streetAddress);
    }
}
