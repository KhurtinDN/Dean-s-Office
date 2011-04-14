package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.ReferenceModel;

import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 10:17 AM
 */
@SuppressWarnings({"NonSerializableServiceParameters"})
@RemoteServiceRelativePath("GWTServices/ReferenceService")
public interface ReferenceService extends RemoteService {

    PagingLoadResult<ReferenceModel> loadAllReferences(PagingLoadConfig pagingLoadConfig);
    PagingLoadResult<ReferenceModel> loadNotIssuedReferences(PagingLoadConfig pagingLoadConfig);

    PagingLoadResult<ReferenceModel> loadRegisteredReferences(PagingLoadConfig pagingLoadConfig);
    PagingLoadResult<ReferenceModel> loadProcessedReferences(PagingLoadConfig pagingLoadConfig);
    PagingLoadResult<ReferenceModel> loadReadyReferences(PagingLoadConfig pagingLoadConfig);
    PagingLoadResult<ReferenceModel> loadIssuedReferences(PagingLoadConfig pagingLoadConfig);

    void removeReferences(List<Long> referenceIdList);
    void updateDestinationReference(Long referenceId, String destination);

    void registrationReference(ReferenceModel.ReferenceType type, Long ownerId);
    void printReferences(List<Long> referenceIdList);
    void readyReferences(List<Long> referenceIdList);
    void issueReferences(List<Long> referenceIdList);

    public static class App {
        private static final ReferenceServiceAsync ourInstance = GWT.create(ReferenceService.class);

        public static ReferenceServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
