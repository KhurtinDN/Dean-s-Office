package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */
@Entity //@MappedSuperclass
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class DirectiveSourceData extends PersistentItem {
}
