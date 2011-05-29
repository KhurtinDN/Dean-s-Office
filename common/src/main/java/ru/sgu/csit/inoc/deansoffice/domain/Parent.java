package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * User: MesheryakovAV
 * Date: 23.12.10
 * Time: 14:28
 */
@Entity
public class Parent extends Person {
    private String address;

    private String workInfo;
    private String phoneNumbers;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(String workInfo) {
        this.workInfo = workInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Parent that = (Parent) o;

        return super.equals(that) &&
                Objects.equal(this.address, that.address) &&
                Objects.equal(this.workInfo, that.workInfo) &&
                Objects.equal(this.phoneNumbers, that.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                address,
                workInfo,
                phoneNumbers);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("address", address)
                .add("workInfo", workInfo)
                .add("phoneNumbers", phoneNumbers)
                .toString();
    }
}
