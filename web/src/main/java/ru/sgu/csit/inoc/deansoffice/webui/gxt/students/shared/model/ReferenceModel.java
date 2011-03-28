package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 10:05 AM
 */
public class ReferenceModel extends DtoModel {
    private StudentModel student;
    private ReferenceType type;
    private ReferenceState state;

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel studentModel) {
        this.student = studentModel;
    }

    public Date getRegistrationDate() {
        return get("registrationDate");
    }

    public void setRegistrationDate(Date registrationDate) {
        set("registrationDate", registrationDate);
    }

    public ReferenceType getType() {
        return type;
    }

    public void setType(ReferenceType type) {
        this.type = type;
        set("type", type);
    }

    public String getDestination() {
        return get("destination");
    }

    public void setDestination(String destination) {
        set("destination", destination);
    }

    public ReferenceState getState() {
        return state;
    }

    public void setState(ReferenceState state) {
        this.state = state;
        set("state", state);
    }

    public Date getIssueDate() {
        return get("issueDate");
    }

    public void setIssueDate(Date issueDate) {
        set("issueDate", issueDate);
    }

    public enum ReferenceType implements Serializable {REFERENCE_1, REFERENCE_2, REFERENCE_3}
    public enum ReferenceState implements Serializable { REGISTERED, PROCESSED, READY, ISSUED }
}
