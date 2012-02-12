package ru.sgu.csit.inoc.deansoffice;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import ru.sgu.csit.inoc.deansoffice.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class DomainTestUtils {

    public static Administration makeAdministration() {
        Administration administration = new Administration();
        administration.setId(0L);
        administration.setName("SSU");
        administration.setRector(makeRector());

        return administration;
    }

    public static Employee makeRector() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setSex(Person.Sex.MALE);
        employee.setLastName("rector-last");
        employee.setFirstName("rector-first");
        employee.setMiddleName("rector-middle");
        employee.setDegree("rector-degree");
        employee.setPosition("rector-pos");

        return employee;
    }

    public static Faculty makeFaculty() {
        Faculty faculty = new Faculty();
        faculty.setId(2L);
        faculty.setFullName("test-faculty");
        faculty.setShortName("tst-fac");
        faculty.setAdministration(makeAdministration());
        faculty.setDean(makeDean());

        return faculty;
    }

    public static Employee makeDean() {
        Employee dean = new Employee();
        dean.setId(3L);
        dean.setSex(Person.Sex.FEMALE);
        dean.setLastName("dean-last");
        dean.setFirstName("dean-first");
        dean.setMiddleName("dean-middle");
        dean.setDegree("dean-degree");
        dean.setPosition("dean-pos");

        return dean;
    }

    public static Speciality makeSpeciality() {
        Speciality speciality = new Speciality();
        speciality.setId(4L);
        speciality.setName("test-speciality");
        speciality.setShortName("tst-spec");
        speciality.setCode("test-code");
        speciality.setCourseCount(5);
        speciality.setFaculty(makeFaculty());
        
        return speciality;
    }

    public static Group makeGroup() {
        Group group = new Group();
        group.setId(5L);
        group.setName("group-name");
        group.setCourse(3);
        group.setSpeciality(makeSpeciality());
        
        return group;
    }

    public static Parent makeMother() {
        Parent mother = new Parent();
        mother.setId(6L);
        mother.setSex(Person.Sex.FEMALE);
        mother.setLastName("mother-last");
        mother.setFirstName("mother-first");
        mother.setMiddleName("mother-middle");
        mother.setAddress("test-mother-address");
        mother.setWorkInfo("test-mother-work-info");
        mother.setPhoneNumbers("test-mother-phone-number");

        return mother;
    }

    public static Parent makeFather() {
        Parent father = new Parent();
        father.setId(7L);
        father.setSex(Person.Sex.MALE);
        father.setLastName("father-last");
        father.setFirstName("father-first");
        father.setMiddleName("father-middle");
        father.setAddress("test-father-address");
        father.setWorkInfo("test-father-work-info");
        father.setPhoneNumbers("test-father-phone-number");

        return father;
    }

    public static Student makeStudent() {
        Student student = new Student();
        student.setId(8L);
        student.setBirthday(new Date(111L));
        student.setSex(Person.Sex.MALE);
        student.setLastName("Иванов");
        student.setFirstName("Иван");
        student.setMiddleName("Иванович");
        student.setLastNameGenitive("Иванова");
        student.setFirstNameGenitive("Ивана");
        student.setMiddleNameGenitive("Ивановича");
        student.setLastNameDative("Иванову");
        student.setFirstNameDative("Ивану");
        student.setMiddleNameDative("Ивановичу");
        student.setGroup(makeGroup());
        student.setSpeciality(makeSpeciality());
        student.setDivision(Student.Division.EVENINGSTUDY);
        student.setStudyForm(Student.StudyForm.BUDGET);
        student.setRole(Student.Role.CAPTAIN);
        student.setEnrollmentOrder(makeEnrollmentOrder());
        student.setReleaseDate(new Date(555L));
        student.setAdditionalData(makeAdditionalStudentData());
        student.setStipends(makeStipendList());

        return student;
    }

    public static Passport makePassport() {
        Passport passport = new Passport();
        passport.setId(9L);

        return passport;
    }

    public static List<Passport> makePassportList() {
        return ImmutableList.of(makePassport());
    }

    public static Photo makePhoto() {
        Photo photo = new Photo();
        photo.setId(10L);
        photo.setFileName("test-file-name");
        photo.setData(null);

        return photo;
    }

    public static Student.AdditionalStudentData makeAdditionalStudentData() {
        Student.AdditionalStudentData additionalStudentData = new Student.AdditionalStudentData();
        additionalStudentData.setId(11L);
        additionalStudentData.setBirthPlace("test-birth-place");
        additionalStudentData.setEducation("test-education");
        additionalStudentData.setWorkInfo("test-work-info");
        additionalStudentData.setMaritalStatus("test-marital-status");
        additionalStudentData.setChildrenInfo("test-children-info");
        additionalStudentData.setPassports(makePassportList());
        additionalStudentData.setMother(makeMother());
        additionalStudentData.setFather(makeFather());
        additionalStudentData.setOldAddress("test-old-address");
        additionalStudentData.setActualAddress("test-actual-address");
        additionalStudentData.setPhoto(makePhoto());

        return additionalStudentData;
    }

    public static Stipend makeStipend() {
        Stipend stipend = new Stipend();
        stipend.setId(12L);
        stipend.setType(Stipend.StipendType.SOCIAL);
        stipend.setStartDate(new Date(1L));
        stipend.setEndDate(new Date(2L));
        stipend.setValue(666);

        return stipend;
    }

    public static Set<Stipend> makeStipendList() {
        return ImmutableSet.of(makeStipend());
    }

    public static EnrollmentOrder makeEnrollmentOrder() {
        EnrollmentOrder enrollmentOrder = new EnrollmentOrder();
        enrollmentOrder.setId(13L);
        enrollmentOrder.setNumber("test-number");
        enrollmentOrder.setSignedDate(new Date(333L));
        enrollmentOrder.setPrintTemplate(makeTemplate());
        enrollmentOrder.setState(Order.OrderState.COMPLETED);
        enrollmentOrder.setDirectives(ImmutableList.<Directive>of(new Directive1(), new Directive2()));
        enrollmentOrder.setData(makeOrderData());
        enrollmentOrder.setEnrollmentDate(new Date(444L));

        return enrollmentOrder;
    }

    public static Template makeTemplate() {
        Template template = new Template("test-file-name");
        template.setId(14L);
        template.setType(Template.TemplType.XML);

        return template;
    }

    public static Order.OrderData makeOrderData() {
        Order.OrderData orderData = new Order.OrderData();
        orderData.setId(15L);
        orderData.setNote("test-note");
        orderData.setSupervisor(makeLeader());
        orderData.setCoordinators(makeCoordinatorList());

        return orderData;
    }

    public static Employee makeLeader() {
        Employee leader = new Employee();
        leader.setId(16L);
        leader.setLastName("leader-last");
        leader.setFirstName("leader-first");
        leader.setMiddleName("leader-middle");
        leader.setDegree("leader-degree");
        leader.setPosition("leader-position");

        return leader;
    }

    public static List<Employee> makeCoordinatorList() {
        return ImmutableList.of(makeCoordinator());
    }

    public static Employee makeCoordinator() {
        Employee coordinator = new Employee();
        coordinator.setId(17L);
        coordinator.setLastName("coordinator-last");
        coordinator.setFirstName("coordinator-first");
        coordinator.setMiddleName("coordinator-middle");
        coordinator.setDegree("coordinator-degree");
        coordinator.setPosition("coordinator-position");

        return coordinator;
    }
}
