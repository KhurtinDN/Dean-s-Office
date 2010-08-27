package ru.sgu.csit.inoc.deansoffice.domain;

import java.util.Date;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 12:26:27 PM
 */
public class Document extends PersistentItem {
    private String number;
    private Date signedDate;

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
}
