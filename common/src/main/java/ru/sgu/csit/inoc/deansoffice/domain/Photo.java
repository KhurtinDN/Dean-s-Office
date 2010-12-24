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

    public String getFormat() {
        String header = new String(data, 0, 10).trim();

        if (header.contains("PNG")) {
            return "png";
        } else if (header.contains("GIF89")) {
            return "gif";
        } else if (header.contains("BM")) {
            return "bmp";
        } else if (header.contains("JFIF")) {
            return "jpg";
        } else {
            return "wbmp";
        }
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
