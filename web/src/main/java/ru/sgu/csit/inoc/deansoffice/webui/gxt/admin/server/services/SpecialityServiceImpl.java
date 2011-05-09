package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.FacultyUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.SpecialityUtil;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 10:52 PM
 */
@Service("AdminSpecialityService")
public class SpecialityServiceImpl implements SpecialityService {
    @Autowired
    private FacultyDAO facultyDAO;
    @Autowired
    private SpecialityDAO specialityDAO;

    @Override
    public List<SpecialityModel> loadSpecialities(Long facultyId) {
        Faculty faculty = facultyDAO.findById(facultyId);
        List<Speciality> specialityList = specialityDAO.findByFacultyId(facultyId);

        FacultyModel facultyModel = FacultyUtil.convertFacultyToFacultyModel(faculty, null);

        return SpecialityUtil.convertSpecialityListToSpecialityModelList(specialityList, facultyModel);
    }

    @Override
    public SpecialityModel loadSpeciality(Long specialityId) {
        Speciality speciality = specialityDAO.findById(specialityId);
        return SpecialityUtil.convertSpecialityToSpecialityModel(speciality);
    }

    @Override
    public SpecialityModel createSpeciality(Long facultyId) {
        Faculty faculty = facultyDAO.findById(facultyId);

        Speciality speciality = new Speciality();
        speciality.setShortName("[Имя]");
        speciality.setName("[Полное имя]");
        speciality.setCode("[код]");
        speciality.setFaculty(faculty);

        specialityDAO.save(speciality);

        return SpecialityUtil.convertSpecialityToSpecialityModel(speciality);
    }

    @Override
    public void updateSpeciality(SpecialityModel specialityModel) {
        Faculty faculty = null;

        if (specialityModel.getFaculty() != null) {
            faculty = facultyDAO.findById(specialityModel.getFaculty().getId());
        }

        Speciality speciality = SpecialityUtil.convertSpecialityModelToSpeciality(specialityModel, faculty);

        specialityDAO.update(speciality);
    }

    @Override
    public void deleteSpecialities(List<Long> specialityIdList) {
        for (Long specialityId : specialityIdList) {
            Speciality speciality = specialityDAO.findById(specialityId); // todo: remove by ID
            specialityDAO.delete(speciality);
        }
    }
}
