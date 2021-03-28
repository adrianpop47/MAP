package repository.file;

import domain.Flight;
import domain.Ticket;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketFile extends AbstractFileRepository<Long, Ticket>{
    public TicketFile(String fileName) {
        super(fileName);
    }

    @Override
    public Ticket extractEntity(List<String> attributes) throws ParseException {
        LocalDateTime purchase;
        LocalDateTime landingTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        purchase = LocalDateTime.parse(attributes.get(3), formatter);
        Ticket ticket = new Ticket(Long.parseLong(attributes.get(0)), attributes.get(1), Long.parseLong(attributes.get(2)),purchase);
        return ticket;
    }

    @Override
    protected String createEntityAsString(Ticket entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = entity.getPurchaseTime();
        String formattedDateTime = dateTime.format(formatter);
        return entity.getId().intValue() + ";" + entity.getUsername() + ";" + entity.getFlightId() + ";" +
                formattedDateTime;
    }
}
