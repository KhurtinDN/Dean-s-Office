package ru.sgu.csit.inoc.deansoffice.services.impl;

import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.services.StudentService;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 11, 2010
 * Time: 12:50:30 PM
 */
public class StudentServiceImpl implements StudentService {
    public boolean equalsFullName(Student student1, Student student2) {
        return (student1.getFirstName().compareTo(student2.getFirstName()) == 0 &&
                student1.getMiddleName().compareTo(student2.getMiddleName()) == 0 &&
                student1.getLastName().compareTo(student2.getLastName()) == 0);
    }
}
