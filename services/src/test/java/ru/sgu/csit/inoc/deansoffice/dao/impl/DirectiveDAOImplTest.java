package ru.sgu.csit.inoc.deansoffice.dao.impl;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 09.03.11
 * Time: 10:05
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.DirectiveDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Directive;

import java.util.*;

/**
 * Unit test for SudentDAOImpl.
 */
public class DirectiveDAOImplTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DirectiveDAOImplTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(DirectiveDAOImplTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static DirectiveDAO directiveDAO = applicationContext.getBean(DirectiveDAO.class);
    private static List<Directive> directives;
    static {
         directives = directiveDAO.findAll();
    }

    public void testDescription() {
        System.out.println("-- testDescription -----");
        for (Directive directive : directives) {
            System.out.println(directive.getType());
            System.out.println(directive.getData().getDescription());
        }
        System.out.println("------------------------\n");
    }
}
