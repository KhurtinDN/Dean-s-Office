package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;

import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 10:17 AM
 */
@RemoteServiceRelativePath("GWTServices/ReferenceService")
public interface ReferenceService extends RemoteService {

    List<ReferenceModel> loadAllReferences();
    List<ReferenceModel> loadNotIssuedReferences();

    List<ReferenceModel> loadRegisteredReferences();
    List<ReferenceModel> loadProcessedReferences();
    List<ReferenceModel> loadReadyReferences();
    List<ReferenceModel> loadIssuedReferences();

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
