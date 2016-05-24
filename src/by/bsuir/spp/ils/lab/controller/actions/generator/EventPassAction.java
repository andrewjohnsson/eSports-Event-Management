package by.bsuir.spp.ils.lab.controller.actions.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.generator.CSVGenerator;
import by.bsuir.spp.ils.lab.generator.ExcelGenerator;
import by.bsuir.spp.ils.lab.generator.PDFGenerator;
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

public class EventPassAction extends ActionSupport {
	private User user;
	private Event event;
	private HttpServletResponse response;
	private PDFGenerator pdfGenerator;
	private CSVGenerator csvGenerator;
	private String docType;

	public EventPassAction(){
		response = ServletActionContext.getResponse();
		pdfGenerator = PDFGenerator.getInstance();
		csvGenerator = CSVGenerator.getInstance();
	}

	public String execute() throws DocumentException, IOException {
		String fileName = "\"pass";
		try {
			switch (getDocType()) {
				case "PDF": {
					response.setHeader("Content-Disposition", "attachment; filename=\"pass.pdf\"");
					response.setContentType("application/pdf");

					Document document = new Document();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ServletOutputStream outputStream = response.getOutputStream();

					PdfWriter writer = PdfWriter.getInstance(document, baos);
					writer.setEncryption("123".getBytes(), "123".getBytes(), ~(PdfWriter.ALLOW_COPY), PdfWriter.ENCRYPTION_AES_128);

					document.open();
					pdfGenerator.generateEventPass(document, getEvent(), getUser(), response.getOutputStream());
					document.close();
					baos.writeTo(outputStream);

					outputStream.flush();
					outputStream.close();
				}
				break;
				case "CSV": {
					fileName += ".csv";
					response.setContentType("text/csv");
					response.setHeader("Content-Disposition",
									"attachment;filename=" + fileName);
					csvGenerator.generateEventPass(event, user, response.getOutputStream());
				}
				break;
				case "EXCEL": {
					ExcelGenerator excelGenerator = ExcelGenerator.getInstance();
					fileName += ".xls\"";
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition",
									"attachment;filename=" + fileName);
					HSSFWorkbook book = excelGenerator.generateEventPass(getEvent(), getUser());
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

	public User getUser() {return user;}
	public void setUser(User person) {this.user = person;}

	public Event getEvent() {return event;}
	public void setEvent(Event event) {this.event = event;}

	public String getDocType() {return docType;}
	public void setDocType(String val) {this.docType = val;}
}