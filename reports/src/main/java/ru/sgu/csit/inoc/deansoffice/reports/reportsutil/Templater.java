package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 10.03.11
 * Time: 10:43
 */
public class Templater {
    private Configuration cfg = new Configuration();

    public Templater(String pathTemplateDir) throws IOException {
        cfg.setDirectoryForTemplateLoading(new File(pathTemplateDir));
        cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER); //new DefaultObjectWrapper()
    }

    public void process(String templName, Map root, OutputStream outputStream)
            throws IOException, TemplateException {
        Template temp = cfg.getTemplate(templName);
        Writer out = new OutputStreamWriter(outputStream);
        temp.process(root, out);
        out.flush();
    }
}
