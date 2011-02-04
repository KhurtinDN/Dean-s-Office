package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * .
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
}
