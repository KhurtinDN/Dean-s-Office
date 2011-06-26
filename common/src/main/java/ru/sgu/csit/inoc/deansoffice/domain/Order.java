package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.*;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 12:28:25 PM
 */
//@MappedSuperclass
@Entity
@Table(name = "`Order`")
public class Order extends Document {
    private OrderState state;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name="directives_index")   // http://jroller.com/eyallupu/entry/hibernate_exception_simultaneously_fetch_multiple
                                            // http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html_single/#collections-indexed
    private List<Directive> directives = new ArrayList<Directive>();

    @OneToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private OrderData data;

    public Order() {
        state = Order.OrderState.CREATED;
    }

    public void setDirectives(final List<Directive> directives) {
        this.directives = directives;
    }

    public void addDirective(final Directive directive) {
        directives.add(directive);
    }

    public List<Directive> getDirectives() {
        return directives;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(final OrderState state) {
        this.state = state;
    }

    public OrderData getData() {
        return data;
    }

    public void setData(final OrderData data) {
        this.data = data;
    }

    public static enum OrderState {
        CREATED, IN_PROCESS, COMPLETED
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Order that = (Order) o;

        return super.equals(that) &&
                Objects.equal(this.state, that.state) &&
                Objects.equal(this.directives, that.directives) &&
                Objects.equal(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                state,
                directives,
                data);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("state", state)
                .add("directives", directives)
                .add("data", data)
                .toString();
    }

    @Entity
    public static class OrderData extends PersistentItem {
        private String note;
        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Leader supervisor;

        @ElementCollection(fetch = FetchType.EAGER)
        @OrderColumn
        private List<Coordinator> coordinators = new ArrayList<Coordinator>();

        public String getNote() {
            return note;
        }

        public void setNote(final String note) {
            this.note = note;
        }

        public Leader getSupervisor() {
            return supervisor;
        }

        public void setSupervisor(final Leader supervisor) {
            this.supervisor = supervisor;
        }

        @ElementCollection
        public List<Coordinator> getCoordinators() {
            return coordinators;
        }

        public void addCoordinator(final Coordinator coordinator) {
            coordinators.add(coordinator);
        }

        public void setCoordinators(final List<Coordinator> coordinators) {
            this.coordinators = coordinators;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final OrderData that = (OrderData) o;

            return super.equals(that) &&
                    Objects.equal(this.note, that.note) &&
                    Objects.equal(this.supervisor, that.supervisor) &&
                    Objects.equal(this.coordinators, that.coordinators);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(
                    supervisor.hashCode(),
                    note,
                    supervisor,
                    coordinators);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .addValue(super.toString())
                    .add("note", note)
                    .add("supervisor", supervisor)
                    .add("coordinators", coordinators)
                    .toString();
        }

        public static OrderData make(
                final Long id,
                final String note,
                final Leader supervisor,
                final List<Coordinator> coordinators) {

            final OrderData orderData = new OrderData();
            orderData.setId(id);
            orderData.setNote(note);
            orderData.setSupervisor(supervisor);
            orderData.setCoordinators(coordinators);

            return orderData;
        }
    }
}
