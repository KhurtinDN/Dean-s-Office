package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 11:29
 */
@Entity //@MappedSuperclass
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  // Маппинг наследования - http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html_single/#inheritance
public abstract class Directive extends PersistentItem {
    public static final String APPOINT_CAPTAINS = "Назначение старост";
    public static final String APPOINT_SOCIAL_STIPEND = "Назначение выплаты социальной стипендии";

    @OneToOne(cascade = CascadeType.ALL)
    protected DirectiveData data = new DirectiveData();
    protected String type;

    public DirectiveData getData() {
        return data;
    }

    public void setData(final DirectiveData data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Directive that = (Directive) o;

        return super.equals(that) &&
                Objects.equal(this.data, that.data) &&
                Objects.equal(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                data,
                type);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("data", data)
                .add("type", type)
                .toString();
    }

    @Entity
    public static class DirectiveData extends PersistentItem {
        private String description = "";
        private String body = "";
        private String grounds = "";
        @OneToOne(cascade = CascadeType.ALL)
        @PrimaryKeyJoinColumn
        private DirectiveSourceData sourceData;

        public String getDescription() {
            return description;
        }

        public void setDescription(final String description) {
            this.description = description;
        }

        public String getBody() {
            return body;
        }

        public void setBody(final String body) {
            this.body = body;
        }

        public String getGrounds() {
            return grounds;
        }

        public void setGrounds(final String grounds) {
            this.grounds = grounds;
        }

        public DirectiveSourceData getSourceData() {
            return sourceData;
        }

        public void setSourceData(final DirectiveSourceData sourceData) {
            this.sourceData = sourceData;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final DirectiveData that = (DirectiveData) o;

            return super.equals(that) &&
                    Objects.equal(this.description, that.description) &&
                    Objects.equal(this.body, that.body) &&
                    Objects.equal(this.grounds, that.grounds) &&
                    Objects.equal(this.sourceData, that.sourceData);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(
                    super.hashCode(),
                    description,
                    body,
                    grounds,
                    sourceData);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .addValue(super.toString())
                    .add("description", description)
                    .add("body", body)
                    .add("grounds", grounds)
                    .add("sourceData", sourceData)
                    .toString();
        }
    }
}
