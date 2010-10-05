package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 12:28:25 PM
 */
@MappedSuperclass
@Table(name = "`Order`")
public class Order extends Document {
}
