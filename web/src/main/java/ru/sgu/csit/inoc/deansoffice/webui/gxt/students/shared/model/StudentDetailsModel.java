package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/7/11
 * Time: 12:26 PM
 */
public class StudentDetailsModel extends StudentModel {

    public String getBirthplace() {
        return get("birthplace");
    }

    public void setBirthplace(String birthplace) {
        set("birthplace", birthplace);
    }

    public String getEducation() {
        return get("education");
    }

    public void setEducation(String education) {
        set("education", education);
    }

    public String getWorkInfo() {
        return get("workInfo");
    }

    public void setWorkInfo(String workInfo) {
        set("workInfo", workInfo);
    }

    public List<PassportModel> getPassports() {
        return get("passports");
    }

    public void setPassports(List<PassportModel> passports) {
        set("passports", passports);
    }

    public String getMaritalStatus() {
        return get("maritalStatus");
    }

    public void setMaritalStatus(String maritalStatus) {
        set("maritalStatus", maritalStatus);
    }

    public String getChildrenInfo() {
        return get("childrenInfo");
    }

    public void setChildrenInfo(String childrenInfo) {
        set("childrenInfo", childrenInfo);
    }

    public ParentModel getFather() {
        return get("father");
    }

    public void setFather(ParentModel father) {
        set("father", father);
    }

    public ParentModel getMother() {
        return get("mother");
    }

    public void setMother(ParentModel mother) {
        set("mother", mother);
    }

    public AddressModel getOldAddress() {
        return get("oldAddress");
    }

    public void setOldAddress(AddressModel oldAddress) {
        set("oldAddress", oldAddress);
    }

    public AddressModel getActualAddress() {
        return get("actualAddress");
    }

    public void setActualAddress(AddressModel actualAddress) {
        set("actualAddress", actualAddress);
    }
}
