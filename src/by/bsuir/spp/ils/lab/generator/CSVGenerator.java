package by.bsuir.spp.ils.lab.generator;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.entity.Ticket;
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
import java.util.*;

/**
 * Created by dreusly on 09/05/16.
 */
public class CSVGenerator {
    private static final CSVGenerator instance = new CSVGenerator();

    public static CSVGenerator getInstance(){
        return instance;
    }

    public CSVGenerator(){
    }

    public static CellProcessor[] getProcessorsForEventPass(){
			final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(),
				new NotNull(),
				new NotNull(),
				new NotNull(),
				new NotNull()
			};
			return processors;
    }

		public static CellProcessor[] getProcessorsForReport(){
			final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(),
				new NotNull(),
				new NotNull()
			};
			return processors;
    }

		public static CellProcessor[] getProcessorsForTicket(){
			final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(),
				new NotNull(),
				new NotNull(),
				new NotNull()
			};
			return processors;
		}

		public void generateEventPass(Event event, User user, OutputStream stream) throws IOException {
			ICsvListWriter listWriter = null;
			listWriter = new CsvListWriter(new OutputStreamWriter(stream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

			final String[] header = {"Event Date","Name","Issue date","Name","Email"};
			final List<Object> eventData = new ArrayList<Object>();
			final CellProcessor[] processors = getProcessorsForEventPass();

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date today = Calendar.getInstance().getTime();
			String reportDate = df.format(today);

			eventData.add(event.getDate());
			eventData.add(event.getName());
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

    public void generateEventReport(List<Event> eventsList, Map<Integer, List<Team>> participants, OutputStream stream) throws IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(stream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

				final String[] header = {"Event Name","Date","Participants"};
        final List<Object> eventData = new ArrayList<Object>();
        final CellProcessor[] processors = getProcessorsForReport();

        eventData.add(eventsList.get(0).getDate());
        eventData.add(eventsList.get(0).getName());
        eventData.add(participants.get(0).get(0).getName());

				listWriter.writeHeader(header);
        listWriter.write(eventData, processors);

				eventData.clear();

        if (listWriter != null) {
            listWriter.close();
        }
    }

		public void generateUserReport(List<User> usersList, OutputStream stream) throws IOException {
			ICsvListWriter listWriter = null;
			listWriter = new CsvListWriter(new OutputStreamWriter(stream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
			final String[] header = {"User Name", "Age", "Email"};

			final List<String> usersData = new ArrayList<String>();
			final CellProcessor[] processors = getProcessorsForReport();

			usersList.forEach(user -> {
				usersData.add(user.getId(), user.getName() + " " + user.getAge() + " " + user.getEmail());
			});
			listWriter.writeHeader(header);
			listWriter.write(usersData, processors);
			usersData.clear();

			if (listWriter != null) {
				listWriter.close();
			}
		}

		public void generateTicket(Event event, Team team, User user, Ticket ticket, OutputStream stream) throws IOException {
			ICsvListWriter listWriter = null;
			listWriter = new CsvListWriter(new OutputStreamWriter(stream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
			final String[] header = {"Event Name","Team Name", "Name", "Seat", "Issue date"};
			final List<Object> eventData = new ArrayList<Object>();
			final CellProcessor[] processors = getProcessorsForTicket();

			eventData.add(event.getName());
			eventData.add(team.getName());
			eventData.add(user.getName());
			eventData.add(ticket.getSeat());
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date today = Calendar.getInstance().getTime();
			String reportDate = df.format(today);
			eventData.add(reportDate);
			listWriter.writeHeader(header);
			listWriter.write(eventData, processors);
			eventData.clear();

			if (listWriter != null) {
				listWriter.close();
			}
		}

}