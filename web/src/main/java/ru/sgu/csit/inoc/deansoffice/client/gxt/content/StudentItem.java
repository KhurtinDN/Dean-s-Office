package ru.sgu.csit.inoc.deansoffice.client.gxt.content;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 2, 2010
 * Time: 3:47:04 PM
 */
public class StudentItem extends BaseModel {

    public StudentItem(Long studentId, Long nn, String name, String studentIdNumber, String division, String studyForm) {
        set("id", studentId);
        set("nn", nn);
        set("name", name);
        set("studentIdNumber", studentIdNumber);
        set("division", division);
        set("studyForm", studyForm);
    }
}
