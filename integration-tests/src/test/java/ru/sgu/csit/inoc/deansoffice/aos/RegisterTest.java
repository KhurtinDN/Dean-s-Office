package ru.sgu.csit.inoc.deansoffice.aos;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.*;
import ru.sgu.csit.inoc.deansoffice.domain.*;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:46
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegisterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterTest.class);

    @Autowired
    GroupDAO groupDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDataDAO orderDataDAO;

    @Autowired
    DirectiveDAO directiveDAO;

    @Autowired
    DirectiveSourceDataDAO directiveSourceDataDAO;

    @Autowired
    Directive1DAO directive1DAO;

    @Autowired
    DirectiveDataDAO directiveDataDAO;

    @Autowired
    SourceData1DAO sourceData1DAO;

    @Autowired
    EmployeeDAO employeeDAO;

    private static List<Employee> leaders = Lists.newArrayList();
    //private static Register register;

    static {
        Employee rector = new Employee();
        rector.setPosition("Ректор СГУ");
        rector.setDegree("профессор, д.ф.-м.н.");
        rector.setFirstName("Леонид");
        rector.setMiddleName("Юрьевич");
        rector.setLastName("Коссович");

        leaders.add(rector);
    }

    @Test
    public void createOrder() {
        LOGGER.info("-- testCreateOrder -----");
        Register register = new Register(leaders);
        register.createNewOrder();
        assertTrue("Создание приказа.",
                register.getCurrentOrder().getState().equals(Order.OrderState.CREATED));
        LOGGER.info("------------------------\n");
    }

    @Test
    public void makeDirective() {
        LOGGER.info("-- testMakeDirective ---");
        Register register = new Register(leaders);
        register.createNewOrder();
        Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);

        assertTrue("Создание директивы type1.",
                !dirData.getDescription().isEmpty());
        LOGGER.info(dirData.getDescription());
        LOGGER.info("------------------------\n");
    }

    @Test
    public void addDirective() {
        LOGGER.info("-- testAddDirective ----");
        Register register = new Register(leaders);
        register.createNewOrder();

        Directive.DirectiveData dirData = register.makeDirective(Directive.APPOINT_CAPTAINS);
        final String NEW_DESC = "Новое описание";
        dirData.setDescription(NEW_DESC);
        register.addDirective(dirData);

        assertTrue("Добавление директивы type1.",
                register.getCurrentOrder().getDirectives().get(0)
                        .getData().getDescription().equals(NEW_DESC));
        LOGGER.info(register.getCurrentOrder().getDirectives().get(0)
                .getData().getDescription());
        LOGGER.info(register.getCurrentOrder().getDirectives().get(0)
                .getData().getGrounds());
        LOGGER.info("------------------------\n");
    }

    @Test
    public void enterOrderData() {
        LOGGER.info("-- testEnterOrderData --");
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
        LOGGER.info(register.getCurrentOrder().getData().getNote());
        LOGGER.info(register.getCurrentOrder().getData()
                .getSupervisor().generateShortName(true));
        LOGGER.info("------------------------\n");
    }

    @Test
    public void endCreateOrder() {
        LOGGER.info("-- testEndCreateOrder --");
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
            LOGGER.info(order.getData().getNote() + " : " + order.getState());
        }
        LOGGER.info("------------------------\n");
    }

//    @Test
//    public void approveOrder() {
//        LOGGER.info("-- testApproveOrder ----");
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
//            LOGGER.info(order.getData().getNote() + " : " + order.getState());
//        }
//        LOGGER.info("Провожу приказ...");
//        register.approveOrder("1-01-1", new Date());
//        assertTrue("Проведение приказа.", register.getOrders().get(0)
//                .getState().equals(Order.OrderState.COMPLETED));
//        for (Order order : register.getOrders()) {
//            LOGGER.info(order.getData().getNote() + " : " + order.getState());
//        }
//        LOGGER.info("Провожу приказ...");
//        register.approveOrder("1-01-1", new Date());
//        assertTrue("Проведение приказа.", register.getOrders().get(1)
//                .getState().equals(Order.OrderState.COMPLETED));
//        for (Order order : register.getOrders()) {
//            LOGGER.info(order.getData().getNote() + " : " + order.getState());
//        }
//        LOGGER.info("------------------------\n");
//    }

    @Test
    public void saveOrderFromDB() {
        LOGGER.info("-- testSaveOrderFromDB ----");
        Register register = new Register(leaders);

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
            LOGGER.info(order.getData().getNote() + " : " + order.getState());
        }
        LOGGER.info("Сохраняю приказ...");
        Order.OrderData currentOrderData = register.getCurrentOrder().getData();
        employeeDAO.save(currentOrderData.getSupervisor());
        orderDataDAO.save(currentOrderData);
        for (Directive directive : register.getCurrentOrder().getDirectives()) {
//            if (Directive.APPOINT_CAPTAINS.equals(directive.getType())) {
//              directiveSourceDataDAO.save(directive.getData().getSourceData());
            directiveDataDAO.save(directive.getData());
            directiveDAO.save(/*(Directive1)*/ directive);
//            }
        }
        orderDAO.save(register.getCurrentOrder());
        LOGGER.info("------------------------\n");
    }

    @Test
    public void approveOrderFromDB() {
        LOGGER.info("-- testApproveOrderFromDB ----");
        Register register = new Register(leaders);

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
            LOGGER.info(order.getData().getNote() + " : " + order.getState());
        }
        LOGGER.info("Сохраняю приказ...");
        Order.OrderData currentOrderData = register.getCurrentOrder().getData();
        employeeDAO.save(currentOrderData.getSupervisor());
        orderDataDAO.save(currentOrderData);
        for (Directive directive : register.getCurrentOrder().getDirectives()) {
//            if (Directive.APPOINT_CAPTAINS.equals(directive.getType())) {
            directiveSourceDataDAO.save(directive.getData().getSourceData());
            directiveDataDAO.save(directive.getData());
            directiveDAO.save(/*(Directive1)*/ directive);
//            }
        }
        orderDAO.save(register.getCurrentOrder());
        LOGGER.info("Провожу приказ...");
        register.approveOrder("1-01-1", new Date());
        assertTrue("Проведение приказа.", register.getOrders().get(0)
                .getState().equals(Order.OrderState.COMPLETED));
        for (Order order : register.getOrders()) {
            LOGGER.info(order.getData().getNote() + " : " + order.getState());
        }
//        LOGGER.info("Провожу приказ...");
//        register.approveOrder("1-01-1", new Date());
//        assertTrue("Проведение приказа.", register.getOrders().get(1)
//                .getState().equals(Order.OrderState.COMPLETED));
//        for (Order order : register.getOrders()) {
//            LOGGER.info(order.getData().getNote() + " : " + order.getState());
//        }
        LOGGER.info("------------------------\n");
    }
}
