package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * Created by XX (MesheryakovAV)
 * Date: 05.10.2010
 * Time: 10:10:09
 */
@Entity
public class Rector extends Leader {
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .toString();
    }
}
