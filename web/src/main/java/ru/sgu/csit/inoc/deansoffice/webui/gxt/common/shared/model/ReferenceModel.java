package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 10:05 AM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class ReferenceModel extends DtoModel {
    private StudentModel student;
    private ReferenceType type;
    private ReferenceState state;

    public StudentModel getStudent() {
        return get("student");
    }

    public void setStudent(StudentModel student) {
        set("student", student);
    }

    public Date getRegistrationDate() {
        return get("registrationDate");
    }

    public void setRegistrationDate(Date registrationDate) {
        set("registrationDate", registrationDate);
    }

    public ReferenceType getType() {
        return get("type");
    }

    public void setType(ReferenceType type) {
        set("type", type);
    }

    public String getDestination() {
        return get("destination");
    }

    public void setDestination(String destination) {
        set("destination", destination);
    }

    public ReferenceState getState() {
        return get("state");
    }

    public void setState(ReferenceState state) {
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
