package repository.file;

import domain.Client;
import domain.Flight;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightFile extends AbstractFileRepository<Long, Flight>{
    public FlightFile(String fileName) {
        super(fileName);
    }

    @Override
    public Flight extractEntity(List<String> attributes) throws ParseException {
        LocalDateTime departureTime;
        LocalDateTime landingTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        departureTime = LocalDateTime.parse(attributes.get(3), formatter);
        landingTime = LocalDateTime.parse(attributes.get(4), formatter);
        Flight flight = new Flight(Long.parseLong(attributes.get(0)),
                attributes.get(1), attributes.get(2),
                departureTime, landingTime, Integer.parseInt(attributes.get(5)));
        return flight;
    }

    @Override
    protected String createEntityAsString(Flight entity) {
        return null;
    }
}
