package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;

import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 10:25 AM
 */
public interface ReferenceServiceAsync {
    void loadAllReferences(AsyncCallback<List<ReferenceModel>> async);
    void loadNotIssuedReferences(AsyncCallback<List<ReferenceModel>> async);

    void loadRegisteredReferences(AsyncCallback<List<ReferenceModel>> async);
    void loadProcessedReferences(AsyncCallback<List<ReferenceModel>> async);
    void loadReadyReferences(AsyncCallback<List<ReferenceModel>> async);
    void loadIssuedReferences(AsyncCallback<List<ReferenceModel>> async);

    void removeReferences(List<Long> referenceIdList, AsyncCallback<Void> async);
    void updateDestinationReference(Long referenceId, String destination, AsyncCallback<Void> async);

    void registrationReference(ReferenceModel.ReferenceType type, Long ownerId, AsyncCallback<Void> async);
    void printReferences(List<Long> referenceIdList, AsyncCallback<Void> async);
    void readyReferences(List<Long> referenceIdList, AsyncCallback<Void> async);
    void issueReferences(List<Long> referenceIdList, AsyncCallback<Void> async);
}
