package by.bsuir.spp.ils.lab.controller.actions.generator;

import by.bsuir.spp.ils.lab.generator.CSVGenerator;
import by.bsuir.spp.ils.lab.generator.ExcelGenerator;
import by.bsuir.spp.ils.lab.generator.PDFGenerator;
import by.bsuir.spp.ils.lab.service.UserService;
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
 * Created by andrewjohnsson on 10.05.16.
 */
public class UserReportAction extends ActionSupport {
	private UserService userService;
	private HttpServletResponse response;
	private PDFGenerator pdfGenerator;
	private CSVGenerator csvGenerator;
	private String docType;

	public UserReportAction(){
		userService = new UserService();
		response = ServletActionContext.getResponse();
		pdfGenerator = PDFGenerator.getInstance();
		csvGenerator = CSVGenerator.getInstance();
	}

	public String execute() throws DocumentException, IOException {
		String fileName = "\"report";
		try {
			switch (getDocType()) {
				case "PDF": {
					Document document = new Document();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					PdfWriter.getInstance(document, baos);
					document.open();
					pdfGenerator.generateUserReport(document, userService.list());
					ServletOutputStream outputStream = response.getOutputStream() ;
					baos.writeTo(outputStream);
					response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf\"");
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
					csvGenerator.generateUserReport(userService.list(), response.getOutputStream());
				}
				break;

				case "EXCEL": {
					ExcelGenerator excelGenerator = ExcelGenerator.getInstance();
					fileName += ".xls\"";
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition",
						"attachment;filename=" + fileName);
					HSSFWorkbook book = excelGenerator.generateUserReport(userService.list());
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
}
