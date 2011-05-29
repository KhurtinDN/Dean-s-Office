package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:28
 */
@Entity //@MappedSuperclass
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class DirectiveSourceData extends PersistentItem {
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .toString();
    }
}
