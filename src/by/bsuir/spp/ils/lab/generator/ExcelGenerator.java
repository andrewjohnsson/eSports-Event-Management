package by.bsuir.spp.ils.lab.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.entity.Ticket;
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
import java.util.List;
import java.util.Map;

/**
 * Created by dreusly on 09/05/16.
 */
public class ExcelGenerator {
    private String reportDate;

    private static final ExcelGenerator instance = new ExcelGenerator();

    public static ExcelGenerator getInstance(){
        return instance;
    }

    private ExcelGenerator(){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        reportDate = df.format(today);
    }

    public HSSFWorkbook generateEventPass(Event event, User user) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Pass for "+ user.getName());
        int i = 0;

        HSSFRow headRow = sheet.createRow(i);
        headRow.createCell(0).setCellValue(event.getName());
        headRow.createCell(1).setCellValue("");
        headRow.createCell(2).setCellValue("");
        i++;

        HSSFRow eventNameRow = sheet.createRow(i);
        eventNameRow.createCell(0).setCellValue("");
        eventNameRow.createCell(1).setCellValue("Event name:");
        eventNameRow.createCell(2).setCellValue(event.getName().toString());
        i++;

        HSSFRow eventDateRow = sheet.createRow(i);
        eventDateRow.createCell(0).setCellValue("");
        eventDateRow.createCell(1).setCellValue("Event date:");
        eventDateRow.createCell(2).setCellValue(event.getDate());
        i++;

        HSSFRow EventInviteGettingDate = sheet.createRow(i);
        EventInviteGettingDate.createCell(0).setCellValue("");
        EventInviteGettingDate.createCell(1).setCellValue("Issue date:");
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

    public HSSFWorkbook generateEventReport(List<Event> eventList, Map<Integer, List<Team>> participants) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Event Report");
				int i = 0;

				for(int j=0;j<eventList.size();j++) {
					StringBuilder teams = new StringBuilder();
					participants.get(eventList.get(j).getId()).forEach(team -> {
						teams.append(team.getName());
						teams.append(",");
					});
					HSSFRow eventNameRow = sheet.createRow(i);
					eventNameRow.createCell(0).setCellValue("");
					eventNameRow.createCell(1).setCellValue("Event name:");
					eventNameRow.createCell(2).setCellValue(eventList.get(j).getName());
					i++;
					HSSFRow eventDateRow = sheet.createRow(i);
					eventDateRow.createCell(0).setCellValue("");
					eventDateRow.createCell(1).setCellValue("Event date:");
					eventDateRow.createCell(2).setCellValue(eventList.get(j).getDate());
					i++;
					HSSFRow eventParticipantsRow = sheet.createRow(i);
					eventParticipantsRow.createCell(0).setCellValue("");
					eventParticipantsRow.createCell(1).setCellValue("Event participants:");
					eventParticipantsRow.createCell(2).setCellValue(teams.toString());
					i++;
            sheet.createRow(i);
            i++;
            sheet.createRow(i);
            i++;
				}

        return workbook;
    }

    public HSSFWorkbook generateUserReport(List<User> userList) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Event Report");
        int i = 0;
        return workbook;
    }

    public HSSFWorkbook generateTicket(Event event, Team team, User user, Ticket ticket) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Ticket #" + ticket.getId());
        int i = 0;

        HSSFRow headRow = sheet.createRow(i);
        headRow.createCell(0).setCellValue("Ticket #"+ticket.getId());
        headRow.createCell(1).setCellValue("");
        headRow.createCell(2).setCellValue("");
        i++;

        HSSFRow eventNameRow = sheet.createRow(i);
        eventNameRow.createCell(0).setCellValue("");
        eventNameRow.createCell(1).setCellValue("Event name:");
        eventNameRow.createCell(2).setCellValue(event.getName());
        i++;

        HSSFRow teamNameRow = sheet.createRow(i);
        teamNameRow.createCell(0).setCellValue("");
        teamNameRow.createCell(1).setCellValue("Team name:");
        teamNameRow.createCell(2).setCellValue(team.getName());
        i++;

        HSSFRow usernameRow = sheet.createRow(i);
        usernameRow.createCell(0).setCellValue("");
        usernameRow.createCell(1).setCellValue("Name:");

        usernameRow.createCell(2).setCellValue(user.getName());
        i++;


        HSSFRow issueDateRow = sheet.createRow(i);
        issueDateRow.createCell(0).setCellValue("");
        issueDateRow.createCell(1).setCellValue("Issue date:");
        issueDateRow.createCell(2).setCellValue(reportDate);
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
