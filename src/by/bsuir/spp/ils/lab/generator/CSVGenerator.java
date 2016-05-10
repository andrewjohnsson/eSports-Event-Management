package by.bsuir.spp.ils.lab.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.User;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dreusly on 09/05/16.
 */
public class CSVGenerator {
    private static final CSVGenerator instance = new CSVGenerator();

    public static CSVGenerator getInstance(){
        return instance;
    }

    private CSVGenerator(){
    }

    private static CellProcessor[] getProcessorsForEventInvite(){
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull()
        };

        return processors;
    }

    public void generateEventInvite(Event event, User user, OutputStream stream) throws IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(stream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        final String[] header = {"Event Date","Name","Invite getting date","Name","Email"};
        final List<Object> eventData = new ArrayList<Object>();
        final CellProcessor[] processors = getProcessorsForEventInvite();

        eventData.add(event.getDate());
        eventData.add(event.getName());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        eventData.add(reportDate);
        eventData.add(user.getName());
        eventData.add(user.getEmail());
        listWriter.writeHeader(header);
        listWriter.write(eventData, processors);
        eventData.clear();

        if (listWriter != null) {
            listWriter.close();
        }
    }



}
