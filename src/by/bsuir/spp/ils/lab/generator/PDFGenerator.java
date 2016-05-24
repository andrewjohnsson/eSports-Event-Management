package by.bsuir.spp.ils.lab.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.entity.Ticket;
import by.bsuir.spp.ils.lab.entity.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dreusly on 09/05/16.
 */
public class PDFGenerator {
	private String reportDate;
	private static final PDFGenerator instance = new PDFGenerator();

	public static PDFGenerator getInstance(){
			return instance;
	}
	BaseFont baseFont;

	private PDFGenerator(){
		try{baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);}
		catch (Exception e){}
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		reportDate = df.format(today);
	}

	public Document generateEventPass(Document doc, Event event, User user, OutputStream stream) throws DocumentException, IOException {
			Image image = Image.getInstance(new URL("http://localhost:8080/bg.png"));
			image.setAbsolutePosition(250f, 450f);
			image.scaleToFit(100f, 100f);
			doc.add(image);
			Paragraph title = new Paragraph("Event Pass\nDate " + event.getDate(), FontFactory.getFont(FontFactory.HELVETICA,
							18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
			title.setAlignment(Element.ALIGN_CENTER);
			doc.add(title);
			doc.add(Chunk.NEWLINE);
			BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
			Font font = new Font(baseFont,11,Font.BOLD);
			doc.add(new Chunk("Event Name:", font));
			doc.add(new Phrase(String.valueOf(event.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
			doc.add(Chunk.NEWLINE);
			doc.add(Chunk.NEWLINE);

			doc.add(new Chunk("Issue date:", font));

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

			return doc;
	}

	public Document generateTicket(Document document, Event event, Team team, User user, Ticket ticket) throws DocumentException, IOException {
		Document doc = document;
		PdfWriter.getInstance(doc, new FileOutputStream("pass.pdf"));
		doc.open();
		Image image = Image.getInstance(new URL("http://localhost:8080/bg.png"));
		doc.add(image);
		Paragraph title = new Paragraph("Ticket # " + ticket.getId(), FontFactory.getFont(FontFactory.HELVETICA,
			18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
		title.setAlignment(Element.ALIGN_CENTER);
		doc.add(title);
		doc.add(Chunk.NEWLINE);
		BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font font = new Font(baseFont,11,Font.BOLD);

		doc.add(new Chunk("Event Name:", font));
		doc.add(new Phrase(String.valueOf(event.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);

		doc.add(new Chunk("Team date:", font));

		doc.add(new Phrase(String.valueOf(team.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);

		doc.add(new Chunk("Name:", font));
		doc.add(new Phrase(String.valueOf(user.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);

		doc.add(new Chunk("Seat:", font));
		doc.add(new Phrase(String.valueOf(ticket.getSeat()), new Font(baseFont, 10, Font.UNDERLINE)));
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);

		doc.add(new Chunk("Issue date:", font));
		doc.add(new Phrase(String.valueOf(reportDate), new Font(baseFont, 10, Font.UNDERLINE)));
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);

		doc.close();

		return doc;
	}

	public Document generateEventReport(Document document, List<Event> eventList, Map<Integer, List<Team>> participants) throws DocumentException, IOException{
		Document doc = document;
		PdfWriter.getInstance(doc, new FileOutputStream("pass.pdf"));
		doc.open();
		Image image = Image.getInstance(new URL("http://localhost:8080/bg.png"));
		doc.add(image);
		Paragraph title = new Paragraph("Event Report\nCreation Date" + reportDate, FontFactory.getFont(
			FontFactory.HELVETICA,
			18,
			Font.ITALIC,
			new CMYKColor(0, 255, 255,17)
		));
		title.setAlignment(Element.ALIGN_CENTER);
		doc.add(title);
		doc.add(Chunk.NEWLINE);
		BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font font = new Font(baseFont,11,Font.BOLD);

		eventList.forEach(event -> {
			try {
				doc.add(Chunk.NEWLINE);
				doc.add(Chunk.NEWLINE);
				StringBuffer teams = new StringBuffer();
				doc.add(new Chunk("Event Name:", font));
				doc.add(new Phrase(String.valueOf(event.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
				doc.add(Chunk.NEWLINE);
				doc.add(new Chunk("Event Date:", font));
				doc.add(new Phrase(String.valueOf(event.getDate()), new Font(baseFont, 10, Font.UNDERLINE)));
				doc.add(Chunk.NEWLINE);
				doc.add(new Chunk("Event Participants:", font));
				participants.get(event.getId()).forEach(team -> {
					teams.append(team.getName());
					teams.append(",");
				});
				teams.substring(teams.length()-2,teams.length());
				doc.add(new Phrase(String.valueOf(teams.toString()), new Font(baseFont, 10, Font.UNDERLINE)));
			}catch (Exception e){}
		});


		doc.close();
		return doc;
	}

	public Document generateUserReport(Document document, List<User> userList) throws DocumentException, IOException{
		Document doc = document;
		PdfWriter.getInstance(doc, new FileOutputStream("pass.pdf"));
		doc.open();
		Image image = Image.getInstance(new URL("http://localhost:8080/bg.png"));
		doc.add(image);
		Paragraph title = new Paragraph("Event Report\nCreation Date" + reportDate, FontFactory.getFont(
			FontFactory.HELVETICA,
			18,
			Font.ITALIC,
			new CMYKColor(0, 255, 255,17)
		));
		title.setAlignment(Element.ALIGN_CENTER);
		doc.add(title);
		doc.add(Chunk.NEWLINE);
		BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font font = new Font(baseFont,11,Font.BOLD);

		userList.forEach(user -> {
			try {
				doc.add(Chunk.NEWLINE);
				doc.add(Chunk.NEWLINE);
				doc.add(new Chunk("User Name:", font));
				doc.add(new Phrase(String.valueOf(user.getName()), new Font(baseFont, 10, Font.UNDERLINE)));
				doc.add(Chunk.NEWLINE);
				doc.add(new Chunk("User Age:", font));
				doc.add(new Phrase(String.valueOf(user.getAge()), new Font(baseFont, 10, Font.UNDERLINE)));
				doc.add(Chunk.NEWLINE);
				doc.add(new Chunk("User Email:", font));
				doc.add(new Phrase(String.valueOf(user.getEmail()), new Font(baseFont, 10, Font.UNDERLINE)));
			}catch (Exception e){}
		});

		doc.close();

		return doc;
	}

}
