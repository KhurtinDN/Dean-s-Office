package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.ReferenceService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 10:26 AM
 */
@Service("ReferenceService")
public class ReferenceServiceImpl implements ReferenceService {
    @Override
    public List<ReferenceModel> loadReferences(boolean all) {
        List<ReferenceModel> referenceModelList = new ArrayList<ReferenceModel>();

        StudentModel studentModel = new StudentModel();
        studentModel.setFullName("Хуртин Денис Николаевич");
        studentModel.setGroupName("511");
        studentModel.setStudyForm(StudentModel.StudyForm.BUDGET);

        ReferenceModel referenceModel = new ReferenceModel();
        referenceModel.setRegistrationDate(new Date());
        referenceModel.setStudent(studentModel);
        referenceModel.setDestination("маме");
        referenceModel.setType(ReferenceModel.ReferenceType.REFERENCE1);
        referenceModel.setStatus(ReferenceModel.Status.REGISTERED);
        referenceModel.setIssueDate(new Date());
        referenceModelList.add(referenceModel);

        studentModel = new StudentModel();
        studentModel.setFullName("Мещеряков Александр Викторович");
        studentModel.setGroupName("411");
        studentModel.setStudyForm(StudentModel.StudyForm.BUDGET);

        referenceModel = new ReferenceModel();
        referenceModel.setRegistrationDate(new Date());
        referenceModel.setStudent(studentModel);
        referenceModel.setDestination("бабушке");
        referenceModel.setType(ReferenceModel.ReferenceType.REFERENCE2);
        referenceModel.setStatus(ReferenceModel.Status.PROCESSED);
        referenceModel.setIssueDate(new Date());
        referenceModelList.add(referenceModel);

        studentModel = new StudentModel();
        studentModel.setFullName("Иванов Иван Иванович");
        studentModel.setGroupName("312");
        studentModel.setStudyForm(StudentModel.StudyForm.COMMERCIAL);

        referenceModel = new ReferenceModel();
        referenceModel.setRegistrationDate(new Date());
        referenceModel.setStudent(studentModel);
        referenceModel.setDestination("тетке в крым");
        referenceModel.setType(ReferenceModel.ReferenceType.REFERENCE3);
        referenceModel.setStatus(ReferenceModel.Status.READY);
        referenceModel.setIssueDate(new Date());
        referenceModelList.add(referenceModel);

        return referenceModelList;
    }
}
