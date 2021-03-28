package repository.file;

import vacante.domain.Client;
import vacante.domain.Reservation;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservationFile extends AbstractFileRepository<Double, Reservation>{
    public ReservationFile(String fileName) {
        super(fileName);
    }

    @Override
    public Reservation extractEntity(List<String> attributes) throws ParseException {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateTime = LocalDateTime.parse(attributes.get(3), formatter);
        Reservation reservation = new Reservation(Double.parseDouble(attributes.get(0)),
                Double.parseDouble(attributes.get(1)),
                Long.parseLong(attributes.get(2)),
                dateTime, Integer.parseInt(attributes.get(4)));
        return reservation;
    }

    @Override
    protected String createEntityAsString(Reservation entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = entity.getStartDate();
        String formattedDateTime = dateTime.format(formatter);
        return entity.getReservationId().intValue() + ";" + entity.getHotelId() + ";" + entity.getClientId() + ";" +
                formattedDateTime + ";" + entity.getNoNights();
    }
}
