package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 23.12.10
 * Time: 15:03
 */
@Entity
public class Address extends PersistentItem {
    // Почтовый индекс или zip-код
    private String postalCode;

    // Страна
    private String country;

    // Республика, край, область, автономный округ, штат
    private String region;

    // Район
    private String district;

    // Населённый пункт
    private String town;

    // Улица, дом, квартира
    private String streetAddress;

    public Address() {
    }

    public Address(String country, String region, String district, String town, String streetAddress) {
        this.country = country;
        this.region = region;
        this.district = district;
        this.town = town;
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
