package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.apache.commons.lang.Validate;
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
 * @author Denis Khurtin
 */
@Service("AdminSpecialityService")
public class SpecialityServiceImpl implements SpecialityService {
    @Autowired
    private FacultyDAO facultyDAO;
    @Autowired
    private SpecialityDAO specialityDAO;

    @Override
    public List<SpecialityModel> loadSpecialities(final Long facultyId) {
        Validate.notNull(facultyId, "facultyId is null");

        final Faculty faculty = facultyDAO.findById(facultyId);
        final List<Speciality> specialityList = specialityDAO.findByFacultyId(facultyId);

        final FacultyModel facultyModel = FacultyUtil.convertFacultyToFacultyModel(faculty, null);

        return SpecialityUtil.convertSpecialityListToSpecialityModelList(specialityList, facultyModel);
    }

    @Override
    public SpecialityModel loadSpeciality(final Long specialityId) {
        Validate.notNull(specialityId, "specialityId is null");

        final Speciality speciality = specialityDAO.findById(specialityId);

        return SpecialityUtil.convertSpecialityToSpecialityModel(speciality);
    }

    @Override
    public SpecialityModel saveOrUpdate(final SpecialityModel specialityModel) {
        Validate.notNull(specialityModel, "specialityModel is null");

        Faculty faculty = null;

        if (specialityModel.getFaculty() != null) {
            faculty = facultyDAO.findById(specialityModel.getFaculty().getId());
        }

        final Speciality speciality = SpecialityUtil.convertSpecialityModelToSpeciality(specialityModel, faculty);

        specialityDAO.saveOrUpdate(speciality);

        return SpecialityUtil.convertSpecialityToSpecialityModel(speciality);
    }

    @Override
    public void deleteSpecialities(final List<Long> specialityIdList) {
        Validate.noNullElements(specialityIdList, "Speciality id list is null or contains null element.");

        for (final Long specialityId : specialityIdList) {
            specialityDAO.deleteById(specialityId);
        }
    }
}
