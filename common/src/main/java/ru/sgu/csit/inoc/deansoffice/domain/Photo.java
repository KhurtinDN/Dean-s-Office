package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 23.12.10
 * Time: 13:15
 */
@Entity
public class Photo extends PersistentItem {
    @Lob
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
