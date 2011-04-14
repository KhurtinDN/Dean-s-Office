package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import java.util.Date;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/8/11
 * Time: 10:46 AM
 */
public class PassportModel extends PersonModel {
    public PassportModel() {
    }

    public boolean isActual() {
        return (Boolean)get("actual");
    }

    public void setActual(boolean actual) {
        set("actual", actual);
    }

    public String getSeries() {
        return get("series");
    }

    public void setSeries(String series) {
        set("series", series);
    }

    public String getNumber() {
        return get("number");
    }

    public void setNumber(String number) {
        set("number", number);
    }

    public String getIssuingOrganization() {
        return get("issuingOrganization");
    }

    public void setIssuingOrganization(String issuingOrganization) {
        set("issuingOrganization", issuingOrganization);
    }

    public Date getIssuedDate() {
        return get("issuedDate");
    }

    public void setIssuedDate(Date issuedDate) {
        set("issuedDate", issuedDate);
    }

    public String getCitizenship() {
        return get("citizenship");
    }

    public void setCitizenship(String citizenship) {
        set("citizenship", citizenship);
    }
}
