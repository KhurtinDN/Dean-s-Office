package ru.sgu.csit.inoc.deansoffice.domain;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 23.12.10
 * Time: 15:03
 */
public class Address {
    // Почтовый индекс или zip-код
    private Integer postalCode;

    // Страна
    private String country;

    // Республика, край, область, автономный округ, штат
    private String region;

    // Район
    private String district;

    // Населённый пункт
    private String town;

    // Улица, дом, квартира
    private String address;

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
