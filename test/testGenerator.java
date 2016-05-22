import by.bsuir.spp.ils.lab.generator.CSVGenerator;
import by.bsuir.spp.ils.lab.generator.ExcelGenerator;
import by.bsuir.spp.ils.lab.generator.PDFGenerator;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testGenerator {
    PDFGenerator pdfGenerator;
    CSVGenerator csvGenerator;
    ExcelGenerator excelGenerator;

    @Test
    public void testPDF(){
        pdfGenerator = PDFGenerator.getInstance();
        assertNotNull(pdfGenerator);
    }

    @Test
    public void testCSV(){
        csvGenerator = CSVGenerator.getInstance();
        assertNotNull(csvGenerator);
    }
    @Test
    public void testExcel(){
        excelGenerator = ExcelGenerator.getInstance();
        assertNotNull(excelGenerator);
    }

}
