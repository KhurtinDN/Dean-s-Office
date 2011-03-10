package ru.sgu.csit.inoc.deansoffice.services.impl;

import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.services.OrderService;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 09.03.11
 * Time: 12:10
 */
public class OrderServiceImpl extends DocumentServiceImpl implements OrderService {
    private Map rootMap = new HashMap();

    public OrderServiceImpl(Document document) {
        super(document);
    }

    @Override
    public void build(Faculty faculty) {
        clear();
        TEXT.put("FACULTY_FULLNAME", faculty.getFullName());
        TEXT.put("FACULTY_SHORTNAME", faculty.getShortName());

        Order order = (Order) document;
        String note = null;
        try {
            note = order.getData().getNote();
        } catch (NullPointerException e) { // если нет примечания, то ничего не делаем
        }
        TEXT.put("ORDER_NOTE", note != null ? ("[" + note + "]") : "");
        Leader supervisor = order.getData().getSupervisor();
        TEXT.put("SUPERVISOR_POSITION", supervisor.getPosition());
        TEXT.put("SUPERVISOR_DEGREE", supervisor.getDegree());
        TEXT.put("SUPERVISOR_NAME", supervisor.generateShortName(true));
        String coordinators = "";
        for (String coordinator : order.getData().getCoordinators()) {
            coordinators += coordinator + "\n";
        }
        TEXT.put("COORDINATORS_LIST", coordinators);

        for (Directive directive : order.getDirectives()) {
            try {
                directive.getData().setBody(new DirectiveServiceImpl().generatePrintTemplateBody(directive));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        rootMap.put("directives", order.getDirectives());
    }

    @Override
    public Map getRootMap() {
        return rootMap;
    }
}
