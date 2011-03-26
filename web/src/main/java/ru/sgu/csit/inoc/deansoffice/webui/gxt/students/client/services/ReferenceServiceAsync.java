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
    void loadReferences(boolean all, AsyncCallback<List<ReferenceModel>> async);

    void updateReference(ReferenceModel referenceModel, AsyncCallback<Boolean> async);

    void registrationReference(ReferenceModel referenceModel, AsyncCallback<Boolean> async);
    void printReferences(List<ReferenceModel> referenceModelList, AsyncCallback<Boolean> async);
    void readyReferences(List<ReferenceModel> referenceModelList, AsyncCallback<Boolean> async);
    void issueReferences(List<ReferenceModel> referenceModelList, AsyncCallback<Boolean> async);
}
