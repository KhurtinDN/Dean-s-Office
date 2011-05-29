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

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public Template getPrintTemplate() {
        return printTemplate;
    }

    public void setPrintTemplate(Template printTemplate) {
        this.printTemplate = printTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Document that = (Document) o;

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
}
