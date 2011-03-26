package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Order;

import java.io.OutputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 09.03.11
 * Time: 12:09
 */
public interface OrderService {
    Map getRootMap();
    void setDefaultPrintTemplate(Order order);
    void generatePrintForm(Order order, Faculty faculty, OutputStream outputStream);
}
