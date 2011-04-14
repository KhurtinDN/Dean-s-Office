package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.ReferenceModel;

import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 10:25 AM
 */
public interface ReferenceServiceAsync {
    void loadAllReferences(PagingLoadConfig pagingLoadConfig, AsyncCallback<PagingLoadResult<ReferenceModel>> async);
    void loadNotIssuedReferences(PagingLoadConfig pagingLoadConfig, AsyncCallback<PagingLoadResult<ReferenceModel>> async);

    void loadRegisteredReferences(PagingLoadConfig pagingLoadConfig, AsyncCallback<PagingLoadResult<ReferenceModel>> async);
    void loadProcessedReferences(PagingLoadConfig pagingLoadConfig, AsyncCallback<PagingLoadResult<ReferenceModel>> async);
    void loadReadyReferences(PagingLoadConfig pagingLoadConfig, AsyncCallback<PagingLoadResult<ReferenceModel>> async);
    void loadIssuedReferences(PagingLoadConfig pagingLoadConfig, AsyncCallback<PagingLoadResult<ReferenceModel>> async);

    void removeReferences(List<Long> referenceIdList, AsyncCallback<Void> async);
    void updateDestinationReference(Long referenceId, String destination, AsyncCallback<Void> async);

    void registrationReference(ReferenceModel.ReferenceType type, Long ownerId, AsyncCallback<Void> async);
    void printReferences(List<Long> referenceIdList, AsyncCallback<Void> async);
    void readyReferences(List<Long> referenceIdList, AsyncCallback<Void> async);
    void issueReferences(List<Long> referenceIdList, AsyncCallback<Void> async);
}
