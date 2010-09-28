package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.Report;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.TemplType;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 12:26:27 PM
 */
@MappedSuperclass
public class Document extends PersistentItem implements Report {
    private String number;
    private Date signedDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Template printTemplate;

    @ElementCollection(fetch = FetchType.EAGER)
    public Map<String, String> TEXT = new HashMap<String, String>();

    public void clear() {
        TEXT.clear();
        TEXT.put("SSU_ONLY_HEADER", "САРАТОВСКИЙ ГОСУДАРСТВЕННЫЙ УНИВЕРСИТЕТ");
        TEXT.put("SSU_BY_HEADER", "ИМЕНИ Н. Г. ЧЕРНЫШЕВСКОГО");
        TEXT.put("SSU_ONLY", "Саратовский государственный университет");
        TEXT.put("SSU_ONLY_GEN", "Саратовского государственного университета");
        TEXT.put("SSU_BY", "имени Н. Г. Чернышевского");
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public Template getPrintTemplate() {
        return printTemplate;
    }

    public void setPrintTemplate(Template printTemplate) {
        this.printTemplate = printTemplate;
    }

    @Override
    public String getVariableValue(String variableName) {
        return TEXT.get(variableName);
    }

    @Override
    public TemplType getTemplateType() {
        return printTemplate.getType();
    }

    @Override
    public String getTemplateFileName() {
        return printTemplate.getFileName();
    }
}
