package by.bsuir.spp.ils.lab.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dreusly on 09/05/16.
 */
public class PDFGenerator {
    private static final PDFGenerator instance = new PDFGenerator();

    public static PDFGenerator getInstance(){
        return instance;
    }
    BaseFont baseFont;
    private PDFGenerator(){
        try{
            baseFont = BaseFont.createFont("/home/cfuck0ff/IdeaProjects/eSports-Event-Management/latto.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        }catch (DocumentException e){
        }catch (IOException e){
        }

    }

    public Document generateEventInvite(Document document, Event event, User user) throws DocumentException, IOException {
        Document doc = document;
        doc.open();
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
        doc.add(new Phrase(String.valueOf(user.getEmail()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.close();

        return doc;
    }

}
