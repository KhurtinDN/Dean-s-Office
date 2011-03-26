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
    private Status status;

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
    }

    public String getDestination() {
        return get("destination");
    }

    public void setDestination(String destination) {
        set("destination", destination);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getIssueDate() {
        return get("issueDate");
    }

    public void setIssueDate(Date issueDate) {
        set("issueDate", issueDate);
    }

    public enum ReferenceType implements Serializable { REFERENCE1, REFERENCE2, REFERENCE3 }
    public enum Status implements Serializable { REGISTERED, PROCESSED, READY, ISSUED }
}
