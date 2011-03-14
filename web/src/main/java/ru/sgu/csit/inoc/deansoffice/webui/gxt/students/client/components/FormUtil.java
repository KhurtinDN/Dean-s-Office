package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/10/11
 * Time: 3:22 PM
 */
public class FormUtil {
    public static FormData formData = new FormData();
    public static FormData wFormData = new FormData("100%");
    public static FormData wh10FormData = new FormData("100%");
    public static FormData h10FormData = new FormData();
    public static FormData wh5FormData = new FormData("100%");
    public static FormData wrFormData = new FormData("100%");

    static {
        wh10FormData.setMargins(new Margins(0, 0, 10, 0));
        h10FormData.setMargins(new Margins(0, 0, 10, 0));
        wh5FormData.setMargins(new Margins(0, 0, 5, 0));
        wrFormData.setMargins(new Margins(0, 20, 0, 0));
    }

    public static HBoxLayoutData flex0 = new HBoxLayoutData();
    public static HBoxLayoutData flex1 = new HBoxLayoutData();
    public static HBoxLayoutData flex2 = new HBoxLayoutData();
    public static HBoxLayoutData flex3 = new HBoxLayoutData();

    public static HBoxLayoutData rFlex0 = new HBoxLayoutData(0, 20, 0, 0);
    public static HBoxLayoutData rFlex1 = new HBoxLayoutData(0, 20, 0, 0);
    public static HBoxLayoutData rFlex2 = new HBoxLayoutData(0, 20, 0, 0);
    public static HBoxLayoutData rFlex3 = new HBoxLayoutData(0, 20, 0, 0);

    static {
        flex1.setFlex(1);
        flex2.setFlex(2);
        flex3.setFlex(3);

        rFlex1.setFlex(1);
        rFlex2.setFlex(2);
        rFlex3.setFlex(3);
    }
}
