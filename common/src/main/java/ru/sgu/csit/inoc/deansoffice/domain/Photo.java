package ru.sgu.csit.inoc.deansoffice.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

/**
 * Created by IntelliJ IDEA.
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
}
