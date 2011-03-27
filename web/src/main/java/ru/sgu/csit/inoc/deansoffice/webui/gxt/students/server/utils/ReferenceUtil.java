package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Reference;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/26/11
 * Time: 10:44 PM
 */
public class ReferenceUtil {
    public static Reference.ReferenceType convertReferenceModelTypeToReferenceType(
            ReferenceModel.ReferenceType type) {

        if (type == null) {
            return null;
        }

        switch (type) {
            case REFERENCE_1:
                return Reference.ReferenceType.REFERENCE_1;
            case REFERENCE_2:
                return Reference.ReferenceType.REFERENCE_2;
            case REFERENCE_3:
                return Reference.ReferenceType.REFERENCE_3;
            default:
                return null;
        }
    }

    public static ReferenceModel.ReferenceType convertReferenceTypeToReferenceModelType(
            Reference.ReferenceType type) {

        if (type == null) {
            return null;
        }

        switch (type) {
            case REFERENCE_1:
                return ReferenceModel.ReferenceType.REFERENCE_1;
            case REFERENCE_2:
                return ReferenceModel.ReferenceType.REFERENCE_2;
            case REFERENCE_3:
                return ReferenceModel.ReferenceType.REFERENCE_3;
            default:
                return null;
        }
    }

    /*public static Reference.ReferenceState convertReferenceModelStateToReferenceState(
            ReferenceModel.ReferenceState referenceState) {

        if (referenceState == null) {
            return null;
        }

        switch (referenceState) {
            case REGISTERED:
                return Reference.ReferenceState.REGISTERED;
            case PROCESSED:
                return Reference.ReferenceState.PROCESSED;
            case READY:
                return Reference.ReferenceState.READY;
            case ISSUED:
                return Reference.ReferenceState.ISSUED;
            default:
                return null;
        }
    }*/

    public static ReferenceModel.ReferenceState convertReferenceStateToReferenceModelState(
            Reference.ReferenceState state) {

        if (state == null) {
            return null;
        }

        switch (state) {
            case REGISTERED:
                return ReferenceModel.ReferenceState.REGISTERED;
            case PROCESSED:
                return ReferenceModel.ReferenceState.PROCESSED;
            case READY:
                return ReferenceModel.ReferenceState.READY;
            case ISSUED:
                return ReferenceModel.ReferenceState.ISSUED;
            default:
                return null;
        }
    }

    public static List<ReferenceModel> convertReferenceListToReferenceModelList(
            StudentDAO studentDAO, List<Reference> referenceList) {

        List<ReferenceModel> referenceModelList = new ArrayList<ReferenceModel>(referenceList.size());

        for (Reference reference : referenceList) {
            ReferenceModel referenceModel = new ReferenceModel();
            referenceModel.setId(reference.getId());
            referenceModel.setRegistrationDate(reference.getRegisteredDate());
            referenceModel.setDestination(reference.getPurpose());
            referenceModel.setState(convertReferenceStateToReferenceModelState(reference.getState()));
            referenceModel.setType(convertReferenceTypeToReferenceModelType(reference.getType()));
            referenceModel.setIssueDate(reference.getIssuedDate());

            Student student = studentDAO.findById(reference.getOwnerId());
            StudentModel studentModel = StudentUtil.convertStudentToStudentDetailsModel(student);
            referenceModel.setStudent(studentModel);

            referenceModelList.add(referenceModel);
        }

        return referenceModelList;
    }
}
