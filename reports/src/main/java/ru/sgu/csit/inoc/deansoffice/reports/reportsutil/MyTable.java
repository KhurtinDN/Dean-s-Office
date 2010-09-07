package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 11:18:10
 */
public class MyTable {
    public PdfPTable table;
    public int border;
    public float borderWidth;

    public MyTable() {
        initData();
    }

    public MyTable(PdfPTable table) {
        initData();
        this.table = table;
    }

    public MyTable(PdfPTable table, int border, float borderWidth) {
        this.table = table;
        this.border = border;
        this.borderWidth = borderWidth;
    }

    private void initData() {
        PdfPCell cell = new PdfPCell();

        border = cell.getBorder();
        borderWidth = cell.getBorderWidth();
    }
}
