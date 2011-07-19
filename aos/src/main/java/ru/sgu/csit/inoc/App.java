package ru.sgu.csit.inoc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.aos.Register;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.LeaderDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StipendDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.services.OrderService;
import ru.sgu.csit.inoc.deansoffice.services.impl.OrderServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    private static StudentDAO studentDAO = applicationContext.getBean(StudentDAO.class);
    private static GroupDAO groupDAO = applicationContext.getBean(GroupDAO.class);
    private static StipendDAO stipendDAO = applicationContext.getBean(StipendDAO.class);
    private static LeaderDAO leaderDAO = applicationContext.getBean(LeaderDAO.class);

    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.loop();
    }

    public static class Shell {
        private static final String ERROR_PREFIX = "[E]";

        private static Scanner scanner = new Scanner(System.in);

        private static List<Leader> leaders = new ArrayList<Leader>();
        private static Register register;

        static {
//            Rector rector = new Rector();
//            rector.setPosition("Ректор СГУ");
//            rector.setDegree("профессор, д.ф.-м.н.");
//            rector.setFirstName("Леонид");
//            rector.setMiddleName("Юрьевич");
//            rector.setLastName("Коссович");

//            leaders.add(rector);
            leaders = leaderDAO.findAll();
            register = new Register(leaders);
        }

        public void loop() {
            print(Mode.INTRO);
            while (process(input())) ;
        }

        static int state = 0;

        private boolean process(String command) {
            if ("help".equalsIgnoreCase(command) || "h".equalsIgnoreCase(command)) {
                print(Mode.ALL_COMMANDS);
            } else if ("exit".equals(command) || "quit".equals(command)) {
                print(Mode.END);
                return false;
            } else if ("test".equals(command)) {
                process("create");
                process("2+");
                process("1");
                process("3");
                process("2");
                process("4");
                process("print");
            } else if ("reset".equals(command)) {
                println("Состояние установлено в начальное");
                state = 0;
            } else if ("create".equals(command)) {
                register.createNewOrder();
                print(Mode.CREATED_ORDER);
                state = 1;
            } else if ("print".equals(command)) {
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(new File("test.pdf"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
//                String simpleTemplName = "order.ftl";
//                String templDir = App.class.getResource("/" + simpleTemplName).getPath()
//                        .replace("%20", " ");
//                templDir = templDir.substring(0, templDir.lastIndexOf("/" + simpleTemplName));
//                println(templDir);
//                String templName = simpleTemplName + ".xml";//templDir + "/" + simpleTemplName;
//                println(templName);
                Order order = register.getCurrentOrder();
//                order.setPrintTemplate(new Template(templName));
                OrderService orderService = new OrderServiceImpl();//applicationContext.getBean(OrderService.class);
                Faculty faculty = new Faculty();
                faculty.setFullName("Компьютерных наук и информационных технологий");
                faculty.setShortName("КНиИТ");
                orderService.generatePrintForm(order, faculty, outputStream);
//                try {
//                    Templater templater = new Templater(templDir);
//                    templater.process(simpleTemplName, orderService.getRootMap(),
//                            new FileOutputStream(new File(templName)));
//                } catch (IOException e) {
//                    throw new RuntimeException("IO Exception in templater.", e);
//                }  catch (TemplateException e) {
//                    throw new RuntimeException("Template Exception in templater.", e);
//                }
//                ReportPdfProcessor.getInstance().generate((Report) orderService, outputStream);
//                print(Mode.GENERATE_PRINT_FORM);
            } else if ("edit".equals(command)) {
                if (register.getCurrentOrder() != null
                        && register.getCurrentOrder().getState() == Order.OrderState.CREATED) {
                    print(Mode.ORDER_INFO);
                    state = 1;
                }
                state = 1;
            } else if (state == 1) {
                if ("1".equals(command)) {
                    println("Введите текст примечания:");
                    register.getCurrentOrderData().setNote(input());
                    register.enterOrderData(register.getCurrentOrderData());
                    print(Mode.UPDATED_ORDER);
                } else if ("2".equals(command)) {
                    println("\"2+\" - добавить директиву в приказ, \"2-\" - удалить.");
                } else if ("2+".equals(command)) {
                    println("Выберите директивы:");
                    for (int i = 0; i < register.getAllDirectives().size(); ++i) {
                        Directive directive = register.getAllDirectives().get(i);

                        println((i + 1) + ". Тип: " + directive.getType());
                        println("   Описание: " + directive.getData().getDescription());
                    }
                    state = 2;
                } else if ("3".equals(command)) {
                    println("Выберите супервизора:");
                    for (int i = 0; i < leaders.size(); ++i) {
                        Leader leader = leaders.get(i);

                        println((i + 1) + ". " + leader.getPosition() + ", " + leader.getDegree() + " - "
                                + leader.generateShortName(true));
                    }
                    state = 3;
                } else if ("4".equals(command)) {
                    for (String coordinator : register.getAllCoordinators()) {
                        register.getCurrentOrderData().addCoordinator(new Coordinator(coordinator));
                    }
                    register.enterOrderData(register.getCurrentOrderData());
                    println("Выбраны все доступные координаторы.");
                    print(Mode.UPDATED_ORDER);
                } else {
                    print(Mode.UNKNOWN_COMMAND);
                }
            } else if (state == 2) {
                try {
                    int index = Integer.valueOf(command) - 1;
                    Directive directive = register.getAllDirectives().get(index);
                    register.makeDirective(directive.getType());
                    Directive.DirectiveData data = directive.getData();
                    println("Тип: " + directive.getType());
                    println("Описание: " + data.getDescription());
                    if (Directive.APPOINT_CAPTAINS.equals(directive.getType())) {
                        Directive1.SourceData sourceData = new Directive1.SourceData();
                        Student student = studentDAO.findById(61L);
                        sourceData.addCaptain(student.getGroup(), student);
                        println(student.generateShortName(false) + " - " + student.getGroup().getName());
                        student = studentDAO.findById(133L);
                        sourceData.addCaptain(student.getGroup(), student);
                        println(student.generateShortName(false) + " - " + student.getGroup().getName());
                        data.setSourceData(sourceData);
                    } else if (Directive.APPOINT_SOCIAL_STIPEND.equals(directive.getType())) {
                        Directive2.SourceData sourceData = new Directive2.SourceData();
                        Stipend stipend = new Stipend();
                        stipend.setType(Stipend.StipendType.SOCIAL);
                        stipend.setValue(198437);
                        stipend.setStartDate(new Date());
                        stipend.setEndDate(new Date());
                        Student student = studentDAO.findById(73L);
                        sourceData.addStudent(student, stipend);
                        println(student.generateShortName(false) + " - " + student.getGroup().getName());
                        student = studentDAO.findById(103L);
                        sourceData.addStudent(student, stipend);
                        println(student.generateShortName(false) + " - " + student.getGroup().getName());
                        student = studentDAO.findById(223L);
                        sourceData.addStudent(student, stipend);
                        println(student.generateShortName(false) + " - " + student.getGroup().getName());
                        student = studentDAO.findById(559L);
                        sourceData.addStudent(student, stipend);
                        println(student.generateShortName(false) + " - " + student.getGroup().getName());
                        data.setSourceData(sourceData);
                    }
                    println("Основания: " + data.getGrounds());
                    register.addDirective(data);
                    print(Mode.UPDATED_ORDER);
                    state = 1;
                } catch (NumberFormatException e) {
                    print(Mode.UNKNOWN_COMMAND);
                } catch (ArrayIndexOutOfBoundsException e) {
                    println(ERROR_PREFIX + "Нет директивы с таким номером");
                }
            } else if (state == 3) {
                try {
                    int index = Integer.valueOf(command) - 1;
                    Leader leader = leaders.get(index);

                    register.getCurrentOrderData().setSupervisor(leader);
                    register.enterOrderData(register.getCurrentOrderData());
                    print(Mode.UPDATED_ORDER);
                    state = 1;
                } catch (NumberFormatException e) {
                    print(Mode.UNKNOWN_COMMAND);
                } catch (ArrayIndexOutOfBoundsException e) {
                    println(ERROR_PREFIX + "Нет супервизора с таким номером");
                }
            } else {
                print(Mode.UNKNOWN_COMMAND);
            }

            return true;
        }

        private String input() {
            print(Mode.PROMPT);
            return scanner.nextLine();
        }

        private void print(Mode mode) {
            switch (mode) {
                case PROMPT:
                    print("> ");
                    break;
                case INTRO:
                    println("=== Запуск программы ===");
                    break;
                case END:
                    println("=== Конец программы ====");
                    break;
                case UNKNOWN_COMMAND:
                    println(ERROR_PREFIX + " Неизвестная комманда");
                    break;
                case CREATED_ORDER:
                    println("Создан приказ");
                    print(Mode.ORDER_INFO);
                    break;
                case UPDATED_ORDER:
                    println("\nПриказ обновлён");
                    print(Mode.ORDER_INFO);
                    break;
                case ORDER_INFO:
                    Order order = register.getCurrentOrder();
                    println("Состояние: " + order.getState());
                    println("1. Примечание: " +
                            (order.getData() != null && order.getData().getNote() != null
                                    ? order.getData().getNote() : ""));
                    println("2. Директивы:");
                    for (int i = 0; i < order.getDirectives().size(); ++i) {
                        Directive directive = order.getDirectives().get(i);
                        println("= " + (i + 1) + " =");
                        println("  Тип: " + directive.getType());
                        println("  Описание: " + directive.getData().getDescription());
                        println("  Основания: " + directive.getData().getGrounds());
                    }
                    println("3. Супервизор: " +
                            (order.getData() != null && order.getData().getSupervisor() != null
                                    ? order.getData().getSupervisor().generateShortName(true) : ""));
                    println("4. Согласовано:");
                    if (order.getData() != null) {
                        for (Coordinator coordinator : order.getData().getCoordinators()) {
                            println("  " + coordinator.getPosition());
                        }
                    }
                    break;
                case ALL_COMMANDS:
                    println("\nСписок всех комманд:");
                    println("help, h - вывод справки по командам");
                    println("exit, quit - завершение работы программы");
                    println("");
                    break;
            }
        }

        private void print(String text) {
            System.out.print(text);
        }

        private void println(String text) {
            System.out.println(text);
        }

        public static enum Mode {
            PROMPT, INTRO, END, ALL_COMMANDS, UNKNOWN_COMMAND,
            CREATED_ORDER, UPDATED_ORDER, ORDER_INFO
        }

//        public static abstract class Command {
//            public List<String> names = new ArrayList<String>();
//
//            public abstract int action();
//            public abstract boolean isThis();
//        }
//
//        public static class CommandHelp extends Command {
//            public CommandHelp() {
//                names.add("help");
//                names.add("h");
//            }
//
//            @Override
//            public int action() {
//                return 0;
//            }
//
//            @Override
//            public boolean isThis() {
//                return false;
//            }
//        }
    }
}
