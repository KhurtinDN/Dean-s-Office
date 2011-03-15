package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/7/11
 * Time: 12:26 PM
 */
public class StudentDetailsModel extends StudentModel {
    private GroupModel group;
    private SpecialityModel speciality;
    private List<PassportModel> passports;
    private ParentModel father;
    private ParentModel mother;
    private String oldAddress;

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }

    public SpecialityModel getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityModel speciality) {
        this.speciality = speciality;
    }

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
        return passports;
    }

    public void setPassports(List<PassportModel> passports) {
        this.passports = passports;
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
        return father;
    }

    public void setFather(ParentModel father) {
        this.father = father;
    }

    public ParentModel getMother() {
        return mother;
    }

    public void setMother(ParentModel mother) {
        this.mother = mother;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(String oldAddress) {
        this.oldAddress = oldAddress;
    }
}
