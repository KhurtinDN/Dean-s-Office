package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * The directive source data.
 */
@Entity
@Table(name = "directive_source_data")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class DirectiveSourceData extends PersistentItem {
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .toString();
    }
}
