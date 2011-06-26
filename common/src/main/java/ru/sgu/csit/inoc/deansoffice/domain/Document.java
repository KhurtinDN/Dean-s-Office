package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 12:26:27 PM
 */
@MappedSuperclass
public class Document extends PersistentItem /* implements Report */ {
    private String number;
    private Date signedDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Template printTemplate;

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(final Date signedDate) {
        this.signedDate = signedDate;
    }

    public Template getPrintTemplate() {
        return printTemplate;
    }

    public void setPrintTemplate(final Template printTemplate) {
        this.printTemplate = printTemplate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Document that = (Document) o;

        return super.equals(that) &&
                Objects.equal(this.number, that.number) &&
                Objects.equal(this.signedDate, that.signedDate) &&
                Objects.equal(this.printTemplate, that.printTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                number,
                signedDate,
                printTemplate);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("number", number)
                .add("signedDate", signedDate)
                .add("printTemplate", printTemplate)
                .toString();
    }

    public static Document make(
            final Long id,
            final String number,
            final Date signedDate,
            final Template template) {

        final Document document = new Document();
        document.setId(id);
        document.setNumber(number);
        document.setSignedDate(signedDate);
        document.setPrintTemplate(template);

        return document;
    }
}
