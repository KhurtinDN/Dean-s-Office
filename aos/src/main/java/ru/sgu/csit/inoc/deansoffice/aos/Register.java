package ru.sgu.csit.inoc.deansoffice.aos;

import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.services.DirectiveService;
import ru.sgu.csit.inoc.deansoffice.services.impl.DirectiveServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 11:23
 */
public class Register {
    private List<Order> orders = new ArrayList<Order>();
    private Order currentOrder;
    private Directive currentDirective;
    private Order.OrderData currentOrderData;
    private List<Employee> leaders = new ArrayList<Employee>();
    private List<String> allCoordinators = new ArrayList<String>();
    private List<Directive> allDirectives = new ArrayList<Directive>();
    private DirectiveProcessor directiveProcessor = new DirectiveProcessor();

    public Register(List<Employee> leaders) {
        allCoordinators.add("Проректор по учебно-организационной работе");
        allCoordinators.add("Начальник учебного управления");
        allCoordinators.add("Начальник юридического отдела");
        allCoordinators.add("Декан факультета");
        allCoordinators.add("Начальник общего отдела");

        allDirectives.add(new Directive1());
        allDirectives.add(new Directive2());

        this.leaders = leaders;
    }

    public void createNewOrder() {
        currentOrder = new Order();
        currentOrderData = new Order.OrderData();
    }

    public Directive.DirectiveData makeDirective(String type) {
        DirectiveService directiveService = new DirectiveServiceImpl();
        currentDirective = directiveService.createDirective(type);
        return currentDirective.getData();
    }

    public void addDirective(Directive.DirectiveData data) {
        if (currentDirective == null) {
            throw new RuntimeException("Please make directive before adding " +
                    "(call makeDirective method).");
        }
        currentDirective.setData(data);
        if (currentOrder == null) {
            throw new RuntimeException("Please create order before adding " +
                    "(call createNewOrder method).");
        }
        currentOrder.addDirective(currentDirective);
    }

    public void enterOrderData(Order.OrderData data) {
        if (currentOrder == null) {
            throw new RuntimeException("Please create order before enter data " +
                    "(call createNewOrder method).");
        }
        currentOrder.setData(data);
    }

    public boolean endCreateOrder() {
        if (currentOrder.getData() != null && currentOrder.getData().getSupervisor() != null) {
            currentOrder.setState(Order.OrderState.IN_PROCESS);
            orders.add(currentOrder);

            return true;
        } else return false;
    }

//    public void generatePrintForm(Order order, FileOutputStream outputStream) {
//    }

    public void approveOrder(String number, Date signedDate) {
        for (Order order : orders) {
            if (order.getState().equals(Order.OrderState.IN_PROCESS)) {
                order.setNumber(number);
                order.setSignedDate(signedDate);
                directiveProcessor.execute(order.getDirectives());
                order.setState(Order.OrderState.COMPLETED);
                break;
            }
        }
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Employee> getLeaders() {
        return leaders;
    }

    public List<String> getAllCoordinators() {
        return allCoordinators;
    }

    public List<Directive> getAllDirectives() {
        return allDirectives;
    }

    public Order.OrderData getCurrentOrderData() {
        return currentOrderData;
    }
}
