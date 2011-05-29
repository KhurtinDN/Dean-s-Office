package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 12:00:05 PM
 */
@Entity
public class Dean extends Leader {
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .toString();
    }
}
