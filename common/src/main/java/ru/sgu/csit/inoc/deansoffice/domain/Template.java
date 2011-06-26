package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 10:51:53
 */
@Entity
public class Template extends PersistentItem {
    private String fileName;
    private TemplType type = TemplType.XML;

    public Template() {
    }

    public Template(final String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public TemplType getType() {
        return type;
    }

    public void setType(final TemplType type) {
        this.type = type;
    }

    public static enum TemplType {
        XML
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Template that = (Template) o;

        return super.equals(that) &&
                Objects.equal(this.fileName, that.fileName) &&
                Objects.equal(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                fileName,
                type);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("fileName", fileName)
                .add("type", type)
                .toString();
    }

    public static Template make(
            final Long id,
            final String fileName,
            final TemplType type) {

        final Template template = new Template();
        template.setId(id);
        template.setFileName(fileName);
        template.setType(type);

        return template;
    }
}
