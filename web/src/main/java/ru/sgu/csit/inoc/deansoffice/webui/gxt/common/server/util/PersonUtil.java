package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util;

import org.apache.commons.lang.Validate;
import ru.sgu.csit.inoc.deansoffice.domain.Person;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PersonModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 3:33 PM
 */
public class PersonUtil {
    public static void populatePersonModelByPerson(final PersonModel personModel, final Person person) {
        Validate.notNull(person, "person is null");
        Validate.notNull(personModel, "personModel is null");

        personModel.setId(person.getId());
        personModel.setFirstName(person.getFirstName());
        personModel.setMiddleName(person.getMiddleName());
        personModel.setLastName(person.getLastName());
        personModel.setFullName(person.getLastName() + ' ' + person.getFirstName() + ' ' + person.getMiddleName());
        personModel.setBirthday(person.getBirthday());
        personModel.setSex( convertPersonSexToPersonModelSex(person.getSex()));
    }

    public static void populatePersonByPersonModel(final Person person, final PersonModel personModel) {
        Validate.notNull(person, "person is null");
        Validate.notNull(personModel, "personModel is null");

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
