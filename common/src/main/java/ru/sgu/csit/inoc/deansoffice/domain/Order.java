package ru.sgu.csit.inoc.deansoffice.domain;

import org.hibernate.annotations.CollectionOfElements;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 12:28:25 PM
 */
@Entity
public class Order extends Document {
    private Map<String, Object> orderData = new HashMap<String, Object>();

    public void addData(String keyWord, Object data) {
        orderData.put(keyWord, data);
    }

    public Object getData(String keyWord) {
        return orderData.get(keyWord);
    }
}
