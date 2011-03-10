package ru.sgu.csit.inoc.deansoffice.aos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.*;
import ru.sgu.csit.inoc.deansoffice.dao.impl.*;
import ru.sgu.csit.inoc.deansoffice.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:46
 */
public class RegisterTest extends TestCase {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static List<Leader> leaders = new ArrayList<Leader>();
    //private static Register register;

    static {
        Leader rector = new Leader();
        rector.setPosition("Ректор СГУ");
        rector.setDegree("профессор, д.ф.-м.н.");
        rector.setFirstName("Леонид");
        rector.setMiddleName("Юрьевич");
        rector.setLastName("Коссович");

        leaders.add(rector);
    }

    public RegisterTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(RegisterTest.class);
    }

    public void testCreateOrder() {
        System.out.println("-- testCreateOrder -----");
        Register register = new Register(leaders);
        register.createNewOrder();
        assertTrue("Создание приказа.",
                register.getCurrentOrder().getState().equals(Order.OrderState.CREATED));
        System.out.println("------------------------\n");
    }

    public void testMakeDirective() {
        System.out.println("-- testMakeDirective ---");
        Register register = new Register(leaders);
        register.createNewOrder();
        Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);

        assertTrue("Создание директивы type1.",
                !dirData.getDescription().isEmpty());
        System.out.println(dirData.getDescription());
        System.out.println("------------------------\n");
    }

    public void testAddDirective() {
        System.out.println("-- testAddDirective ----");
        Register register = new Register(leaders);
        register.createNewOrder();

        Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);
        final String NEW_DESC = "Новое описание";
        dirData.setDescription(NEW_DESC);
        register.addDirective(dirData);

        assertTrue("Добавление директивы type1.",
                register.getCurrentOrder().getDirectives().get(0)
                        .getData().getDescription().equals(NEW_DESC));
        System.out.println(register.getCurrentOrder().getDirectives().get(0)
                        .getData().getDescription());
        System.out.println(register.getCurrentOrder().getDirectives().get(0)
                        .getData().getGrounds());
        System.out.println("------------------------\n");
    }

    public void testEnterOrderData() {
        System.out.println("-- testEnterOrderData --");
        Register register = new Register(leaders);
        register.createNewOrder();

        Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);
        register.addDirective(dirData);

        Order.OrderData orderData = new Order.OrderData();

        final String NOTE = "Примечание приказа";
        orderData.setNote(NOTE);
        orderData.setSupervisor(register.getLeaders().get(0));
        register.enterOrderData(orderData);

        assertTrue("Добавление данных приказа.",
                register.getCurrentOrder().getData().getNote().equals(NOTE));
        System.out.println(register.getCurrentOrder().getData().getNote());
        System.out.println(register.getCurrentOrder().getData()
                .getSupervisor().generateShortName(true));
        System.out.println("------------------------\n");
    }

    public void testEndCreateOrder() {
        System.out.println("-- testEndCreateOrder --");
        Register register = new Register(leaders);
        register.createNewOrder();

        Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);
        register.addDirective(dirData);

        Order.OrderData orderData = new Order.OrderData();
        register.enterOrderData(orderData);
        assertTrue("Завершение создания приказа.", !register.endCreateOrder());

        final String NOTE = "Примечание приказа";
        orderData.setNote(NOTE);
        orderData.setSupervisor(register.getLeaders().get(0));
        register.enterOrderData(orderData);
        assertTrue("Завершение создания приказа.", register.endCreateOrder());
        assertTrue("Завершение создания приказа.", register.getOrders().get(0)
                .getState().equals(Order.OrderState.IN_PROCESS));
        for (Order order : register.getOrders()) {
            System.out.println(order.getData().getNote() + " : " + order.getState());
        }
        System.out.println("------------------------\n");
    }

//    public void testApproveOrder() {
//        System.out.println("-- testApproveOrder ----");
//        Register register = new Register(leaders);
//        for (int i = 0; i < 3; ++i) {
//            register.createNewOrder();
//
//            for (int j = 0; j < 3; ++j) {
//                Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);
//                register.addDirective(dirData);
//            }
//
//            Order.OrderData orderData = new Order.OrderData();
//            orderData.setNote(String.valueOf(i));
//            orderData.setSupervisor(register.getLeaders().get(0));
//            register.enterOrderData(orderData);
//            assertTrue("Завершение создания приказа.", register.endCreateOrder());
//        }
//        for (Order order : register.getOrders()) {
//            System.out.println(order.getData().getNote() + " : " + order.getState());
//        }
//        System.out.println("Провожу приказ...");
//        register.approveOrder("1-01-1", new Date());
//        assertTrue("Проведение приказа.", register.getOrders().get(0)
//                .getState().equals(Order.OrderState.COMPLETED));
//        for (Order order : register.getOrders()) {
//            System.out.println(order.getData().getNote() + " : " + order.getState());
//        }
//        System.out.println("Провожу приказ...");
//        register.approveOrder("1-01-1", new Date());
//        assertTrue("Проведение приказа.", register.getOrders().get(1)
//                .getState().equals(Order.OrderState.COMPLETED));
//        for (Order order : register.getOrders()) {
//            System.out.println(order.getData().getNote() + " : " + order.getState());
//        }
//        System.out.println("------------------------\n");
//    }

    public void testSaveOrderFromDB() {
        System.out.println("-- testSaveOrderFromDB ----");
        Register register = new Register(leaders);

        OrderDAO orderDAO = applicationContext.getBean(OrderDAOImpl.class);
        OrderDataDAO orderDataDAO = applicationContext.getBean(OrderDataDAOImpl.class);
        DirectiveDAO directiveDAO = applicationContext.getBean(DirectiveDAOImpl.class);
        DirectiveSourceDataDAO directiveSourceDataDAO = applicationContext.getBean(DirectiveSourceDataDAOImpl.class);
        Directive1DAO directive1DAO = applicationContext.getBean(Directive1DAOImpl.class);
        DirectiveDataDAO directiveDataDAO = applicationContext.getBean(DirectiveDataDAOImpl.class);
        SourceData1DAO sourceData1DAO = applicationContext.getBean(SourceData1DAOImpl.class);
        LeaderDAO leaderDAO = applicationContext.getBean(LeaderDAOImpl.class);

        for (int i = 0; i < 3; ++i) {
            register.createNewOrder();

            for (int j = 0; j < 3; ++j) {
                Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);
                register.addDirective(dirData);
            }

            Order.OrderData orderData = new Order.OrderData();
            orderData.setNote(String.valueOf(i));
            orderData.setSupervisor(register.getLeaders().get(0));
            register.enterOrderData(orderData);
            assertTrue("Завершение создания приказа.", register.endCreateOrder());
        }
        for (Order order : register.getOrders()) {
            System.out.println(order.getData().getNote() + " : " + order.getState());
        }
        System.out.println("Сохраняю приказ...");
        Order.OrderData currentOrderData = register.getCurrentOrder().getData();
        leaderDAO.save(currentOrderData.getSupervisor());
        orderDataDAO.save(currentOrderData);
        for (Directive directive : register.getCurrentOrder().getDirectives()) {
//            if (Directive.APPOINT_CAPTAINS.equals(directive.getType())) {
//              directiveSourceDataDAO.save(directive.getData().getSourceData());
                directiveDataDAO.save(directive.getData());
                directiveDAO.save(/*(Directive1)*/ directive);
//            }
        }
        orderDAO.save(register.getCurrentOrder());
        System.out.println("------------------------\n");
    }

    public void testApproveOrderFromDB() {
        System.out.println("-- testApproveOrderFromDB ----");
        Register register = new Register(leaders);

        GroupDAO groupDAO = applicationContext.getBean(GroupDAOImpl.class);
        StudentDAO studentDAO = applicationContext.getBean(StudentDAOImpl.class);

        OrderDAO orderDAO = applicationContext.getBean(OrderDAOImpl.class);
        OrderDataDAO orderDataDAO = applicationContext.getBean(OrderDataDAOImpl.class);
        DirectiveDAO directiveDAO = applicationContext.getBean(DirectiveDAOImpl.class);
        DirectiveSourceDataDAO directiveSourceDataDAO = applicationContext.getBean(DirectiveSourceDataDAOImpl.class);
        Directive1DAO directive1DAO = applicationContext.getBean(Directive1DAOImpl.class);
        DirectiveDataDAO directiveDataDAO = applicationContext.getBean(DirectiveDataDAOImpl.class);
        SourceData1DAO sourceData1DAO = applicationContext.getBean(SourceData1DAOImpl.class);
        LeaderDAO leaderDAO = applicationContext.getBean(LeaderDAOImpl.class);

        for (int i = 0; i < 3; ++i) {
            register.createNewOrder();

            //for (int j = 0; j < 3; ++j) {
                Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);
                Directive1.SourceData sourceData = new Directive1.SourceData();

                Group group = groupDAO.findByName("111").get(0);
                Student student = studentDAO.findByGroup(group).get(0);

                sourceData.addCaptain(group, student);
                dirData.setSourceData(sourceData);
                register.addDirective(dirData);
            //}

            Order.OrderData orderData = new Order.OrderData();
            orderData.setNote(String.valueOf(i));
            orderData.setSupervisor(register.getLeaders().get(0));
            register.enterOrderData(orderData);
            assertTrue("Завершение создания приказа.", register.endCreateOrder());
        }
        for (Order order : register.getOrders()) {
            System.out.println(order.getData().getNote() + " : " + order.getState());
        }
        System.out.println("Сохраняю приказ...");
        Order.OrderData currentOrderData = register.getCurrentOrder().getData();
        leaderDAO.save(currentOrderData.getSupervisor());
        orderDataDAO.save(currentOrderData);
        for (Directive directive : register.getCurrentOrder().getDirectives()) {
//            if (Directive.APPOINT_CAPTAINS.equals(directive.getType())) {
                directiveSourceDataDAO.save(directive.getData().getSourceData());
                directiveDataDAO.save(directive.getData());
                directiveDAO.save(/*(Directive1)*/ directive);
//            }
        }
        orderDAO.save(register.getCurrentOrder());
        System.out.println("Провожу приказ...");
        register.approveOrder("1-01-1", new Date());
        assertTrue("Проведение приказа.", register.getOrders().get(0)
                .getState().equals(Order.OrderState.COMPLETED));
        for (Order order : register.getOrders()) {
            System.out.println(order.getData().getNote() + " : " + order.getState());
        }
//        System.out.println("Провожу приказ...");
//        register.approveOrder("1-01-1", new Date());
//        assertTrue("Проведение приказа.", register.getOrders().get(1)
//                .getState().equals(Order.OrderState.COMPLETED));
//        for (Order order : register.getOrders()) {
//            System.out.println(order.getData().getNote() + " : " + order.getState());
//        }
        System.out.println("------------------------\n");
    }
}
