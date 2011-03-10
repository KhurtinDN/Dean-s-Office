package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 11:29
 */
@Entity //@MappedSuperclass
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  // Маппинг наследования - http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html_single/#inheritance
public abstract class Directive extends PersistentItem {
    public static final String APPOINT_CAPTAINS = "Назначение старост";
    public static final String APPOINT_SOCIAL_STIPEND = "Назначение выплаты социальной стипендии";

    @OneToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    protected DirectiveData data;
    protected String type;

    public DirectiveData getData() {
        return data;
    }

    public void setData(DirectiveData data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Entity
    public static class DirectiveData extends PersistentItem {
        private String description = "";
        private String body = "";
        private String grounds = "";
        @OneToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private DirectiveSourceData sourceData;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getGrounds() {
            return grounds;
        }

        public void setGrounds(String grounds) {
            this.grounds = grounds;
        }

        public DirectiveSourceData getSourceData() {
            return sourceData;
        }

        public void setSourceData(DirectiveSourceData sourceData) {
            this.sourceData = sourceData;
        }
    }
}
