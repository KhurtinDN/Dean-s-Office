package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.*;
import java.util.*;

/**
 * .
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

    public void addDirective(Directive directive) {
        directives.add(directive);
    }

    public List<Directive> getDirectives() {
        return directives;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderData getData() {
        return data;
    }

    public void setData(OrderData data) {
        this.data = data;
    }

    public static enum OrderState {
        CREATED, IN_PROCESS, COMPLETED
    }

    @Entity
    public static class OrderData extends PersistentItem {
        private String note;
        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Leader supervisor;

        @ElementCollection(fetch = FetchType.EAGER)
        private Set<String> coordinators = new LinkedHashSet<String>();

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Leader getSupervisor() {
            return supervisor;
        }

        public void setSupervisor(Leader supervisor) {
            this.supervisor = supervisor;
        }

        @ElementCollection
        public Set<String> getCoordinators() {
            return coordinators;
        }

        public void addCoordinator(String coordinator) {
            coordinators.add(coordinator);
        }

        public void setCoordinators(Set<String> coordinators) {
            this.coordinators = coordinators;
        }
    }
}
