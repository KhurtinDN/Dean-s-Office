package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import com.lowagie.text.pdf.PdfPCell;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 11:17:06
 */
public class MyCell {
    public PdfPCell cell;
    public boolean containsTable = false;

    public MyCell() {
    }

    public MyCell(PdfPCell cell) {
        this.cell = cell;
    }

    public MyCell(PdfPCell cell, boolean containsTable) {
        this.cell = cell;
        this.containsTable = containsTable;
    }
}
