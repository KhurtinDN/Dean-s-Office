package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by XX (MesheryakovAV)
 * Date: 07.09.2010
 * Time: 9:18:30
 */
@Entity
public class Reference extends Document {
    private ReferenceType type;
    private ReferenceState state;
    private Date addedDate;
    private Date issuedDate;
    private String purpose;
    private Long ownerId;

    public ReferenceType getType() {
        return type;
    }

    public void setType(ReferenceType type) {
        this.type = type;
    }

    public ReferenceState getState() {
        return state;
    }

    public void setState(ReferenceState state) {
        this.state = state;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public static enum ReferenceType {
        REFERENCE_1, REFERENCE_2, REFERENCE_3;
    }

    public static enum ReferenceState {
        ADDED, PROCESSING, SIGNED, ISSUED;
    }
}
