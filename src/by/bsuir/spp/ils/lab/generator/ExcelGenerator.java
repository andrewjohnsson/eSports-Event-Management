package by.bsuir.spp.ils.lab.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.User;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dreusly on 09/05/16.
 */
public class ExcelGenerator {
    private static final ExcelGenerator instance = new ExcelGenerator();

    public static ExcelGenerator getInstance(){
        return instance;
    }

    private ExcelGenerator(){

    }

    public HSSFWorkbook generateEventInvite(Event event, User user) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Invite to Event" + event.getName()+ " to "+ user.getName());
        int i = 0;

        HSSFRow headRow = sheet.createRow(i);
        headRow.createCell(0).setCellValue("Event #" + event.getId());
        headRow.createCell(1).setCellValue("");
        headRow.createCell(2).setCellValue("");
        i++;

        HSSFRow eventNameRow = sheet.createRow(i);
        eventNameRow.createCell(0).setCellValue("");
        eventNameRow.createCell(1).setCellValue("Event name:");
        eventNameRow.createCell(2).setCellValue(event.getName());
        i++;

        HSSFRow eventDateRow = sheet.createRow(i);
        eventNameRow.createCell(0).setCellValue("");
        eventNameRow.createCell(1).setCellValue("Event date:");
        eventNameRow.createCell(2).setCellValue(event.getDate());
        i++;

        HSSFRow EventInviteGettingDate = sheet.createRow(i);
        EventInviteGettingDate.createCell(0).setCellValue("");
        EventInviteGettingDate.createCell(1).setCellValue("Invite getting date:");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        EventInviteGettingDate.createCell(2).setCellValue(reportDate);
        i++;


        HSSFRow personNameRow = sheet.createRow(i);
        personNameRow.createCell(0).setCellValue("");
        personNameRow.createCell(1).setCellValue("Person name:");
        personNameRow.createCell(2).setCellValue(user.getName());
        i++;

        HSSFRow personEmail = sheet.createRow(i);
        personEmail.createCell(0).setCellValue("");
        personEmail.createCell(1).setCellValue("Person email:");
        personEmail.createCell(2).setCellValue(user.getEmail());
        i++;

        HSSFRow emptyRow = sheet.createRow(i);
        emptyRow.createCell(0).setCellValue("");
        emptyRow.createCell(1).setCellValue("");
        emptyRow.createCell(2).setCellValue("");
        i++;

        cellsStyle(workbook, sheet, i);

        for (int j=0; j < 3 ; j++) {
            sheet.autoSizeColumn(j);
        }

        return workbook;
    }

    private void cellsStyle(HSSFWorkbook workbook, HSSFSheet sheet, int i) {
        for (int j = 0; j < i; j++) {
            CellStyle headCellStyle = workbook.createCellStyle();
            Font font0 = workbook.createFont();
            font0.setBold(true);
            font0.setFontName(HSSFFont.FONT_ARIAL);
            headCellStyle.setFont(font0);
            headCellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            Row row0 = sheet.getRow(j);

            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setItalic(true);
            font.setFontName(HSSFFont.FONT_ARIAL);
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        }
    }
}
