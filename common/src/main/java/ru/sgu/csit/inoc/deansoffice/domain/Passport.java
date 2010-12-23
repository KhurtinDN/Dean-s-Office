package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 23.12.10
 * Time: 14:01
 */
@Entity
public class Passport extends Person {
    private String series;
    private String number;
    private String issuingOrganization;
    private Date issuedDate;

    private String citizenship;
    private Address address;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}