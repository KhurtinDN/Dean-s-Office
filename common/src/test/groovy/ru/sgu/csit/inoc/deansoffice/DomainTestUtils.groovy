package ru.sgu.csit.inoc.deansoffice

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableSet
import ru.sgu.csit.inoc.deansoffice.domain.Student.AdditionalStudentData
import ru.sgu.csit.inoc.deansoffice.domain.*

/**
 * @author Denis Khurtin
 */
public final class DomainTestUtils {

    public static Administration makeAdministration() {
        return new Administration(
                id: 0L,
                name: "SSU",
                rector: makeRector())
    }

    public static Employee makeRector() {
        return new Employee(
                id: 1L,
                sex: Person.Sex.MALE,
                lastName: "rector-last",
                firstName: "rector-first",
                middleName: "rector-middle",
                degree: "rector-degree",
                position: "rector-pos")
    }

    public static Faculty makeFaculty() {
        new Faculty(
                id: 2L,
                fullName: "test-faculty",
                shortName: "tst-fac",
                administration: makeAdministration(),
                dean: makeDean())
    }

    public static Employee makeDean() {
        return new Employee(
                id: 3L,
                sex: Person.Sex.FEMALE,
                lastName: "dean-last",
                firstName: "dean-first",
                middleName: "dean-middle",
                degree: "dean-degree",
                position: "dean-pos")
    }

    public static Speciality makeSpeciality() {
        return new Speciality(
                id: 4L,
                name: "test-speciality",
                shortName: "tst-spec",
                code: "test-code",
                courseCount: 5,
                faculty: makeFaculty())
    }

    public static Group makeGroup() {
        return new Group(
                id: 5L,
                name: "group-name",
                course: 3,
                speciality: makeSpeciality())
    }

    public static Parent makeMother() {
        return new Parent(
                id: 6L,
                sex: Person.Sex.FEMALE,
                firstName: "mother-fir",
                middleName: "mother-mid",
                lastName: "mother-last",
                address: "test-address",
                workInfo: "test-work-info",
                phoneNumbers: "test-phone-number")
    }

    public static Parent makeFather() {
        return new Parent(
                id: 7L,
                sex: Person.Sex.MALE,
                firstName: "father-fir",
                middleName: "father-mid",
                lastName: "father-last",
                address: "test-address",
                workInfo: "test-work-info",
                phoneNumbers: "test-phone-number")
    }

    public static Student makeStudent() {
        new Student(
                id: 8L,
                birthday: new Date(111L),
                sex: Person.Sex.MALE,
                firstName: "Иван",
                middleName: "Иванович",
                lastName: "Иванов",
                firstNameDative: "Ивану",
                middleNameDative: "Ивановичу",
                lastNameDative: "Иванову",
                group: makeGroup(),
                speciality: makeSpeciality(),
                division: Student.Division.EVENINGSTUDY,
                studyForm: Student.StudyForm.BUDGET,
                role: Student.Role.CAPTAIN,
                enrollmentOrder: makeEnrollmentOrder(),
                additionalData: makeAdditionalStudentData(),
                stipends: makeStipendList())
    }

    public static Passport makePassport() {
        return new Passport(id: 9L)
    }

    public static List<Passport> makePassportList() {
        return ImmutableList.of(makePassport())
    }

    public static Photo makePhoto() {
        return new Photo(
                id: 10L,
                fileName: "test-file-name",
                data: null)
    }

    public static AdditionalStudentData makeAdditionalStudentData() {
        new Student.AdditionalStudentData(
                id: 11L,
                photo: makePhoto(),
                birthPlace: "test-birth-place",
                education: "test-edu",
                workInfo: "test-work-info",
                passports: makePassportList(),
                maritalStatus: "test-marital-status",
                childrenInfo: "test-children-info",
                mother: makeMother(),
                father: makeFather(),
                oldAddress: "test-old-address",
                actualAddress: "test-actual-address")
    }

    public static Stipend makeStipend() {
        return new Stipend(
                id: 12L,
                type: Stipend.StipendType.SOCIAL,
                startDate: new Date(1),
                endDate: new Date(2),
                value: 666)
    }

    public static Set<Stipend> makeStipendList() {
        return ImmutableSet.of(
                makeStipend())
    }

    public static EnrollmentOrder makeEnrollmentOrder() {
        new EnrollmentOrder(
                id: 13L,
                number: "test-number",
                signedDate: new Date(333L),
                printTemplate: makeTemplate(),
                state: Order.OrderState.COMPLETED,
                directives: ImmutableList.<Directive> of(new Directive1()),
                data: makeOrderData(),
                enrollmentDate: new Date(444L),
                releaseDate: new Date(555L))
    }

    public static Template makeTemplate() {
        return new Template(
                id: 14L,
                fileName: "test-file-name",
                type: Template.TemplType.XML)
    }

    public static Order.OrderData makeOrderData() {
        return new Order.OrderData(
                id: 15L,
                note: "test-note",
                supervisor: makeLeader(),
                coordinators: makeCoordinatorList())
    }

    public static Employee makeLeader() {
        return new Employee(
                id: 16L,
                firstName: "lead-fir",
                middleName: "lead-mid",
                lastName: "lead-last",
                degree: "lead-deg",
                position: "lead-pos")
    }

    public static List<Employee> makeCoordinatorList() {
        return ImmutableList.of(
                makeCoordinator())
    }

    public static Employee makeCoordinator() {
        return new Employee(
                id: 17L,
                firstName: "coordinator-fir",
                middleName: "coordinator-mid",
                lastName: "coordinator-last",
                degree: "coordinator-deg",
                position: "coordinator-pos")
    }
}
