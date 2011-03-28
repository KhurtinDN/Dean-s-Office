package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.ReferenceDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Reference;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.ReferenceService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.ReferenceUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;

import java.util.*;

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
    public PagingLoadResult<ReferenceModel> loadAllReferences(PagingLoadConfig pagingLoadConfig) {
        List<Reference> referenceList = referenceDAO.findAll();
        List<ReferenceModel> referenceModelList =
                ReferenceUtil.convertReferenceListToReferenceModelList(studentDAO, referenceList);

        return createPagingLoadResult(referenceModelList, pagingLoadConfig);
    }

    @Override
    public PagingLoadResult<ReferenceModel> loadNotIssuedReferences(PagingLoadConfig pagingLoadConfig) { // todo: need implement
        List<ReferenceModel> allReferenceModelList =
                ReferenceUtil.convertReferenceListToReferenceModelList(studentDAO, referenceDAO.findAll());

        List<ReferenceModel> notIssuedReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : allReferenceModelList) {
            if (!ReferenceModel.ReferenceState.ISSUED.equals(referenceModel.getState())) {
                notIssuedReferences.add(referenceModel);
            }
        }

        return createPagingLoadResult(notIssuedReferences, pagingLoadConfig);
    }

    @Override
    public PagingLoadResult<ReferenceModel> loadRegisteredReferences(PagingLoadConfig pagingLoadConfig) { // todo: need implement
        List<ReferenceModel> allReferenceModelList =
                ReferenceUtil.convertReferenceListToReferenceModelList(studentDAO, referenceDAO.findAll());

        List<ReferenceModel> registeredReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : allReferenceModelList) {
            if (ReferenceModel.ReferenceState.REGISTERED.equals(referenceModel.getState())) {
                registeredReferences.add(referenceModel);
            }
        }

        return createPagingLoadResult(registeredReferences, pagingLoadConfig);
    }

    @Override
    public PagingLoadResult<ReferenceModel> loadProcessedReferences(PagingLoadConfig pagingLoadConfig) { // todo: need implement
        List<ReferenceModel> allReferenceModelList =
                ReferenceUtil.convertReferenceListToReferenceModelList(studentDAO, referenceDAO.findAll());

        List<ReferenceModel> processedReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : allReferenceModelList) {
            if (ReferenceModel.ReferenceState.PROCESSED.equals(referenceModel.getState())) {
                processedReferences.add(referenceModel);
            }
        }

        return createPagingLoadResult(processedReferences, pagingLoadConfig);
    }

    @Override
    public PagingLoadResult<ReferenceModel> loadReadyReferences(PagingLoadConfig pagingLoadConfig) { // todo: need implement
        List<ReferenceModel> allReferenceModelList =
                ReferenceUtil.convertReferenceListToReferenceModelList(studentDAO, referenceDAO.findAll());

        List<ReferenceModel> readyReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : allReferenceModelList) {
            if (ReferenceModel.ReferenceState.READY.equals(referenceModel.getState())) {
                readyReferences.add(referenceModel);
            }
        }

        return createPagingLoadResult(readyReferences, pagingLoadConfig);
    }

    @Override
    public PagingLoadResult<ReferenceModel> loadIssuedReferences(PagingLoadConfig pagingLoadConfig) { // todo: need implement
        List<ReferenceModel> allReferenceModelList =
                ReferenceUtil.convertReferenceListToReferenceModelList(studentDAO, referenceDAO.findAll());

        List<ReferenceModel> issuedReferences = new ArrayList<ReferenceModel>();
        for (ReferenceModel referenceModel : allReferenceModelList) {
            if (ReferenceModel.ReferenceState.ISSUED.equals(referenceModel.getState())) {
                issuedReferences.add(referenceModel);
            }
        }

        return createPagingLoadResult(issuedReferences, pagingLoadConfig);
    }

    private PagingLoadResult<ReferenceModel> createPagingLoadResult(List<ReferenceModel> references,
                                                                    PagingLoadConfig config) {
        final String sortField = config.getSortField();
        if (sortField != null) {
            Collections.sort(references, config.getSortDir().comparator(new Comparator<ReferenceModel>() {
                @Override
                public int compare(ReferenceModel reference1, ReferenceModel reference2) {
                    if (sortField.equals("fullName") || sortField.equals("groupName")) {
                        String field1 = reference1.getStudent().get(sortField);
                        String field2 = reference2.getStudent().get(sortField);

                        return field1.compareTo(field2);
                    }

                    Comparable<Object> comparable1 = reference1.get(sortField);
                    Comparable<Object> comparable2 = reference2.get(sortField);

                    if (comparable1 == null || comparable2 == null) {
                        return comparable1 == null && comparable2 == null ? 0 : (comparable1 != null ? 1 : -1);
                    }

                    return comparable1.compareTo(comparable2);
                }
            }));
        }

        int offset = config.getOffset();
        int limit = references.size();

        if (config.getLimit() > 0) {
            limit = Math.min(offset + config.getLimit(), limit);
        }

        if (offset > limit) {
            offset = limit;
        }

        List<ReferenceModel> data = new ArrayList<ReferenceModel>(references.subList(offset, limit));

        return new BasePagingLoadResult<ReferenceModel>(data, offset, references.size());
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
