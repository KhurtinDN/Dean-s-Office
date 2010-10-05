package ru.sgu.csit.inoc.deansoffice.server.servlets;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Reference;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.domain.Template;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 5, 2010
 * Time: 9:11:09 AM
 */
public class DocumentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
        StudentDAO studentDAO = applicationContext.getBean(StudentDAO.class);

        String documentType = request.getRequestURI().substring("/documents/".length());

        if ("reference".equalsIgnoreCase(documentType)) {

            Long studentId;
            try {
                studentId = Long.parseLong(request.getParameter("studentId"));
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            String documentName = request.getParameter("document");

            URL url = DocumentServlet.class.getResource("/templates/" + documentName + ".xml");
            if (url == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            String templateName = url.getFile().replace("%20", " ");

            Student student = studentDAO.findById(studentId);

            if (student == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            
            Reference reference = new Reference();
            reference.setPrintTemplate(new Template(templateName));
            reference.build(student);

            response.setContentType("application/pdf");
            ReportPdfProcessor.getInstance().generate(reference, response.getOutputStream());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
