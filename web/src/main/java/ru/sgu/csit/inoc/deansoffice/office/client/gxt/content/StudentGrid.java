package ru.sgu.csit.inoc.deansoffice.office.client.gxt.content;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.StudentDto;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 7, 2010
 * Time: 11:53:33 PM
 */
public class StudentGrid extends Grid<StudentDto> {
    private static ColumnModel createGridColumnModel() {
        List<ColumnConfig> columnConfigList = new ArrayList<ColumnConfig>();
        columnConfigList.add(new ColumnConfig("nn", "N", 40));
        columnConfigList.add(new ColumnConfig("name", "Имя", 500));
        columnConfigList.add(new ColumnConfig("studyForm", "Форма обучения", 100));

        return new ColumnModel(columnConfigList);
    }

    static ColumnModel columnModel = createGridColumnModel();

    protected StudentGrid() {
        super(new ListStore<StudentDto>(), columnModel);
    }

    public void update(String groupName, List<StudentDto> studentDtoList) {
        ListStore<StudentDto> gridListStore = createStudentGridListStore(groupName, studentDtoList);
        reconfigure(gridListStore, columnModel);
    }

    private ListStore<StudentDto> createStudentGridListStore(String groupName, List<StudentDto> studentDtoList) {
        ListStore<StudentDto> listStore = new ListStore<StudentDto>();

        Long nn = 1L;
        for (StudentDto studentDto : studentDtoList) {
            studentDto.set("nn", nn++);
            studentDto.set("groupName", groupName);
            listStore.add(studentDto);
        }

        return listStore;
    }
}
