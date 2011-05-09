package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import com.extjs.gxt.ui.client.data.ModelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.AdminService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 12:36 PM
 */
@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private SpecialityService specialityService;
    @Autowired
    private GroupService groupService;

    @Override
    public List<ModelData> loadFaculties() {
        return new ArrayList<ModelData>(facultyService.loadFaculties());
    }

    @Override
    public List<ModelData> loadSpecialities(Long facultyId) {
        return new ArrayList<ModelData>(specialityService.loadSpecialities(facultyId));
    }

    @Override
    public List<ModelData> loadGroups(Long specialityId) {
        return new ArrayList<ModelData>(groupService.loadGroups(specialityId));
    }
}
