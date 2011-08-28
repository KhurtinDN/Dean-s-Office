package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Denis Khurtin
 */
public class ReferenceModel extends DtoModel {
    private StudentModel marker1;
    private ReferenceType marker2;
    private ReferenceState marker3;

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

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final ReferenceModel that = (ReferenceModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getStudent(), that.getStudent()) &&
                ObjectUtil.equal(this.getRegistrationDate(), that.getRegistrationDate()) &&
                ObjectUtil.equal(this.getType(), that.getType()) &&
                ObjectUtil.equal(this.getDestination(), that.getDestination()) &&
                ObjectUtil.equal(this.getState(), that.getState()) &&
                ObjectUtil.equal(this.getIssueDate(), that.getIssueDate());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getStudent(),
                getRegistrationDate(),
                getType(),
                getDestination(),
                getState(),
                getIssueDate());
    }
}
