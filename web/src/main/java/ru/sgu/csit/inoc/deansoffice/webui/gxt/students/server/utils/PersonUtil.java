package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Person;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.PersonModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 3:33 PM
 */
public class PersonUtil {
    public static void populatePersonModelByPerson(PersonModel personModel, Person person) {
        if (personModel == null || person == null) {
            throw new IllegalArgumentException("Argument must be not null");
        }

        personModel.setId(person.getId());
        personModel.setFirstName(person.getFirstName());
        personModel.setMiddleName(person.getMiddleName());
        personModel.setLastName(person.getLastName());
        personModel.setFullName(person.getLastName() + ' ' + person.getFirstName() + ' ' + person.getMiddleName());
        personModel.setBirthday(person.getBirthday());
        personModel.setSex( convertPersonSexToPersonModelSex(person.getSex()));
    }

    public static void populatePersonByPersonModel(Person person, PersonModel personModel) {
        if (person == null || personModel == null) {
            throw new IllegalArgumentException("Argument must be not null");
        }

        person.setId(personModel.getId());
        person.setFirstName(personModel.getFirstName());
        person.setMiddleName(personModel.getMiddleName());
        person.setLastName(personModel.getLastName());
        person.setBirthday(personModel.getBirthday());
        person.setSex( convertPersonModelSexToPersonSex(personModel.getSex()));
    }

    public static PersonModel.Sex convertPersonSexToPersonModelSex(Person.Sex sex) {
        if (sex == null) {
            return null;
        }

        switch (sex) {
            case MALE:
                return PersonModel.Sex.MALE;
            case FEMALE:
                return PersonModel.Sex.FEMALE;
            default:
                return null;
        }
    }

    public static Person.Sex convertPersonModelSexToPersonSex(PersonModel.Sex sex) {
        if (sex == null) {
            return null;
        }

        switch (sex) {
            case MALE:
                return Person.Sex.MALE;
            case FEMALE:
                return Person.Sex.FEMALE;
            default:
                return null;
        }
    }
}
