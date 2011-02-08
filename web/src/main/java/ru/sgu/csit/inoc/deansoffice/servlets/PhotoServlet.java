package ru.sgu.csit.inoc.deansoffice.servlets;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.sgu.csit.inoc.deansoffice.dao.PhotoDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Photo;
import ru.sgu.csit.inoc.deansoffice.services.PhotoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 5, 2010
 * Time: 9:11:09 AM
 */
public class PhotoServlet extends HttpServlet {
    private PhotoDAO photoDAO;
    private PhotoService photoService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
        photoDAO = applicationContext.getBean(PhotoDAO.class);
        photoService = applicationContext.getBean(PhotoService.class);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String photoName = request.getRequestURI().substring("/photos/".length());
        String photoIdName = photoName.substring(0, photoName.indexOf('.'));

        if (photoIdName.matches("^\\d+$")) {
            Long photoId = Long.parseLong(photoIdName);

            Photo photo = photoDAO.findById(photoId);

            if (photo != null) {
                response.setContentType("image/jpeg");

                OutputStream outputStream = response.getOutputStream();
                photoService.loadDataToOutputStream(photo.getFileName(), outputStream);
                outputStream.flush();

                return;
            }
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
