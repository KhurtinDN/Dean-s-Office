package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.ReferenceDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Reference;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.ReferenceService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.ReferenceUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 10:26 AM
 */
@Service("ReferenceService")
public class ReferenceServiceImpl implements ReferenceService {
    @Autowired
    private ru.sgu.csit.inoc.deansoffice.services.ReferenceService referenceService;
    @Autowired
    private ReferenceDAO referenceDAO;
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<ReferenceModel> loadAllReferences() {
        List<Reference> referenceList = referenceDAO.findAll();

        return ReferenceUtil.convertReferenceListToReferenceModelList(studentDAO, referenceList);
    }

    @Override
    public List<ReferenceModel> loadNotIssuedReferences() { // todo: need implement
        List<ReferenceModel> notIssuedReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : loadAllReferences()) {
            if (!ReferenceModel.ReferenceState.ISSUED.equals(referenceModel.getState())) {
                notIssuedReferences.add(referenceModel);
            }
        }
        return notIssuedReferences;
    }

    @Override
    public List<ReferenceModel> loadRegisteredReferences() { // todo: need implement
        List<ReferenceModel> registeredReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : loadAllReferences()) {
            if (ReferenceModel.ReferenceState.REGISTERED.equals(referenceModel.getState())) {
                registeredReferences.add(referenceModel);
            }
        }
        return registeredReferences;
    }

    @Override
    public List<ReferenceModel> loadProcessedReferences() { // todo: need implement
        List<ReferenceModel> processedReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : loadAllReferences()) {
            if (ReferenceModel.ReferenceState.PROCESSED.equals(referenceModel.getState())) {
                processedReferences.add(referenceModel);
            }
        }
        return processedReferences;
    }

    @Override
    public List<ReferenceModel> loadReadyReferences() { // todo: need implement
        List<ReferenceModel> readyReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : loadAllReferences()) {
            if (ReferenceModel.ReferenceState.READY.equals(referenceModel.getState())) {
                readyReferences.add(referenceModel);
            }
        }
        return readyReferences;
    }

    @Override
    public List<ReferenceModel> loadIssuedReferences() { // todo: need implement
        List<ReferenceModel> issuedReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : loadAllReferences()) {
            if (ReferenceModel.ReferenceState.ISSUED.equals(referenceModel.getState())) {
                issuedReferences.add(referenceModel);
            }
        }
        return issuedReferences;
    }

    @Override
    public void removeReferences(List<Long> referenceIdList) {
        referenceService.removeReferencesById(referenceIdList);
    }

    @Override
    public void updateDestinationReference(Long referenceId, String destination) {
        Reference reference = referenceDAO.findById(referenceId);
        reference.setPurpose(destination);
        referenceDAO.update(reference);
    }

    @Override
    public void registrationReference(ReferenceModel.ReferenceType type, Long ownerId) {
        Reference reference =
                referenceService.makeReference(ReferenceUtil.convertReferenceModelTypeToReferenceType(type), ownerId);
        referenceService.registrationReference(reference);
    }

    @Override
    public void printReferences(List<Long> referenceIdList) {
        referenceService.printReferencesById(referenceIdList);
    }

    @Override
    public void readyReferences(List<Long> referenceIdList) {
        referenceService.readyReferencesById(referenceIdList);
    }

    @Override
    public void issueReferences(List<Long> referenceIdList) {
        referenceService.issueReferencesById(referenceIdList);
    }
}
