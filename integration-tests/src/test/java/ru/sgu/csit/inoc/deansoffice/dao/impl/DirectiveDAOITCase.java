package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.DirectiveDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Directive;

import java.util.List;

/**
 * User: MesheryakovAV
 * Date: 09.03.11
 * Time: 10:05
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DirectiveDAOITCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectiveDAOITCase.class);

    private List<Directive> directives;

    @Autowired
    private DirectiveDAO directiveDAO;

    @Before
    public void init() {
        directives = directiveDAO.findAll();
    }

    @Test
    public void description() {
        LOGGER.info("-- testDescription -----");

        for (Directive directive : directives) {
            LOGGER.info(directive.getType());
            LOGGER.info(directive.getData().getDescription());
        }
        LOGGER.info("------------------------\n");
    }
}
