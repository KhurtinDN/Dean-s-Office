package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * User: MesheryakovAV
 * Date: 23.12.10
 * Time: 13:15
 */
@Entity
public class Photo extends PersistentItem {
    private String fileName;
    //@Lob
    //@Type(type = "org.hibernate.type.BinaryType")//"org.hibernate.type.PrimitiveByteArrayBlobType")
    //@Column(columnDefinition = "bytea")
    //@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
    //@Type(type = "org.springframework.orm.hibernate3.support.BlobByteArrayType")
    @Transient
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Photo that = (Photo) o;

        return super.equals(that) &&
                Objects.equal(this.fileName, that.fileName) &&
                Objects.equal(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                fileName,
                data);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("fileName", fileName)
                .add("data", data)
                .toString();
    }
}
