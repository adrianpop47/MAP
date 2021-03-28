package validator;

import domain.Client;
import domain.Flight;

public class FlightValidator implements Validator<Flight> {
    @Override
    public void validate(Flight entity) throws ValidationException {
        if (entity.getId() == null)
            throw new ValidationException("id null");
        if (entity.getFrom() == null)
            throw new ValidationException("from null");
        if (entity.getTo() == null)
            throw new ValidationException("to null");
        if (entity.getDepartureTime() == null)
            throw new ValidationException("departure null");
        if (entity.getLandingTime() == null)
            throw new ValidationException("landing null");

    }
}
