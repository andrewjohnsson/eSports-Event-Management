package by.bsuir.spp.ils.lab.controller.actions.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.generator.CSVGenerator;
import by.bsuir.spp.ils.lab.generator.ExcelGenerator;
import by.bsuir.spp.ils.lab.generator.PDFGenerator;
import by.bsuir.spp.ils.lab.service.EventService;
import by.bsuir.spp.ils.lab.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventInviteGenerationAction extends ActionSupport {

    public String execute() throws DocumentException, IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        PDFGenerator pdfGenerator = PDFGenerator.getInstance();
        CSVGenerator csvGenerator = CSVGenerator.getInstance();
        EventService service = new EventService();
        UserService userService = new UserService();
        User user = userService.list().get(1);
        Event event = service.list().get(1);
        String fileName = "\"event_invite";
        String docType = "EXCEL";
        try {
            switch (docType) {
                case "PDF": {
                    Document document = new Document();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PdfWriter.getInstance(document, baos);
                    document.open();
                    Document doc = pdfGenerator.generateEventInvite(document, event, user);
                    ServletOutputStream outputStream = response.getOutputStream() ;
                    baos.writeTo(outputStream);
                    response.setHeader("Content-Disposition", "attachment; filename=\"event_invite.pdf\"");
                    response.setContentType("application/pdf");
                    outputStream.flush();
                    outputStream.close();
                }
                break;

                case "CSV": {
                    fileName += ".csv";
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    csvGenerator.generateEventInvite(event, user, response.getOutputStream());
                }
                break;

                case "EXCEL": {
                    ExcelGenerator excelGenerator = ExcelGenerator.getInstance();
                    fileName += ".xls\"";
                    response.setContentType("text/xls");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    HSSFWorkbook book = excelGenerator.generateEventInvite(event, user);
                    book.write(response.getOutputStream());
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                break;
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public Document generatePDF(Document doc, Event event, User user) throws DocumentException, IOException {

        Paragraph title = new Paragraph("Event\nDate " + event.getDate(), FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
        title.setAlignment(Element.ALIGN_CENTER);
        doc.add(title);
        doc.add(Chunk.NEWLINE);
        BaseFont baseFont = BaseFont.createFont("/home/cfuck0ff/IdeaProjects/eSports-Event-Management/latto.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        Font font = new Font(baseFont,11,Font.BOLD);

        doc.add(new Chunk("Event Name:", font));
        doc.add(new Phrase(String.valueOf(event.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Invite getting date:", font));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);

        doc.add(new Phrase(String.valueOf(reportDate), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Person name:", font));
        doc.add(new Phrase(String.valueOf(user.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Person email:", font));
        doc.add(new Phrase(String.valueOf(user.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        return doc;
    }
}