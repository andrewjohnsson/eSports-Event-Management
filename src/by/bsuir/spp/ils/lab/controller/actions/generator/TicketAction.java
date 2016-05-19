package by.bsuir.spp.ils.lab.controller.actions.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.entity.Ticket;
import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.generator.CSVGenerator;
import by.bsuir.spp.ils.lab.generator.ExcelGenerator;
import by.bsuir.spp.ils.lab.generator.PDFGenerator;
import by.bsuir.spp.ils.lab.service.EventService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by andrewjohnsson on 11.05.16.
 */
public class TicketAction extends ActionSupport {
	private HttpServletResponse response;
	private PDFGenerator pdfGenerator;
	private CSVGenerator csvGenerator;
	private EventService eventService;
	private Event event;
	private User user;
	private Ticket ticket;
	private Team team;
	private String docType;

	public TicketAction(){
		response = ServletActionContext.getResponse();
		pdfGenerator = PDFGenerator.getInstance();
		csvGenerator = CSVGenerator.getInstance();
		eventService = new EventService();
	}

	public String execute() throws DocumentException, IOException {
		String fileName = "\"ticket";
		try {
			switch (getDocType()) {
				case "PDF": {
					Document document = new Document();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					PdfWriter.getInstance(document, baos);
					document.open();
					pdfGenerator.generateTicket(document, getEvent(), getTeam(), getUser(), getTicket());
					ServletOutputStream outputStream = response.getOutputStream() ;
					baos.writeTo(outputStream);
					response.setHeader("Content-Disposition", "attachment; filename=\"ticket.pdf\"");
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
					csvGenerator.generateTicket(getEvent(), getTeam(), getUser(), getTicket(), response.getOutputStream());
				}
				break;

				case "EXCEL": {
					ExcelGenerator excelGenerator = ExcelGenerator.getInstance();
					fileName += ".xls\"";
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition",
						"attachment;filename=" + fileName);
					HSSFWorkbook book = excelGenerator.generateTicket(getEvent(), getTeam(), getUser(), getTicket());
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

	public String getDocType() {return docType;}
	public void setDocType(String val) {this.docType = val;}

	public User getUser() { return user; }
	public void setUser(User person) {
		this.user = person;
	}

	public Event getEvent() { return event; }
	public void setEvent(Event event) {
		this.event = event;
	}

	public Ticket getTicket() { return ticket; }
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Team getTeam() { return team; }
	public void setTeam(Team ticket) {
		this.team = team;
	}
}
