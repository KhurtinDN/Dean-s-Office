package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util;

import org.apache.commons.lang.Validate;
import ru.sgu.csit.inoc.deansoffice.domain.Person;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PersonModel;

/**
 * @author Denis Khurtin
 */
public class PersonUtil {
    public static void populatePersonModelByPerson(final PersonModel personModel, final Person person) {
        Validate.notNull(person, "person is null");
        Validate.notNull(personModel, "personModel is null");

        personModel.setId(person.getId());

        fillNamesFromPerson(personModel, person);

        personModel.setBirthday(person.getBirthday());
        personModel.setSex( convertPersonSexToPersonModelSex(person.getSex()));
    }

    public static void populatePersonByPersonModel(final Person person, final PersonModel personModel) {
        Validate.notNull(person, "person is null");
        Validate.notNull(personModel, "personModel is null");

        person.setId(personModel.getId());

        fillNamesFromPersonModel(person, personModel);

        person.setBirthday(personModel.getBirthday());
        person.setSex( convertPersonModelSexToPersonSex(personModel.getSex()));
    }

    public static void fillNamesFromPerson(final PersonModel personModel, final Person person) {
        Validate.notNull(person, "person is null");
        Validate.notNull(personModel, "personModel is null");

        personModel.setLastName(person.getLastName());
        personModel.setLastNameGenitive(person.getLastNameGenitive());
        personModel.setLastNameDative(person.getLastNameDative());

        personModel.setFirstName(person.getFirstName());
        personModel.setFirstNameGenitive(person.getFirstNameGenitive());
        personModel.setFirstNameDative(person.getFirstNameDative());

        personModel.setMiddleName(person.getMiddleName());
        personModel.setMiddleNameGenitive(person.getMiddleNameGenitive());
        personModel.setMiddleNameDative(person.getMiddleNameDative());

        personModel.setFullName(person.getLastName() + ' ' + person.getFirstName() + ' ' + person.getMiddleName());
    }

    public static void fillNamesFromPersonModel(final Person person, final PersonModel personModel) {
        Validate.notNull(person, "person is null");
        Validate.notNull(personModel, "personModel is null");

        person.setFirstName(personModel.getFirstName());
        person.setFirstNameGenitive(personModel.getFirstNameGenitive());
        person.setFirstNameDative(personModel.getFirstNameDative());

        person.setLastName(personModel.getLastName());
        person.setLastNameGenitive(personModel.getLastNameGenitive());
        person.setLastNameDative(personModel.getLastNameDative());

        person.setMiddleName(personModel.getMiddleName());
        person.setMiddleNameGenitive(personModel.getMiddleNameGenitive());
        person.setMiddleNameDative(personModel.getMiddleNameDative());
    }

    public static PersonModel.Sex convertPersonSexToPersonModelSex(final Person.Sex sex) {
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

    public static Person.Sex convertPersonModelSexToPersonSex(final PersonModel.Sex sex) {
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
