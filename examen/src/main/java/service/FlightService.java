package service;

import domain.Client;
import domain.Flight;
import repository.Repository;
import validator.Validator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FlightService {
    private Repository<Long, Flight> repo;
    private Validator<Flight> validator;

    public FlightService(Repository<Long, Flight> repo, Validator<Flight> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Iterable<Flight> getAll(){
        return repo.findAll();
    }

    public Flight findOne(Long flightId) {
        return repo.findOne(flightId);
    }

    public List<String> getFromLocations() {
        var res = StreamSupport.stream(getAll().spliterator(), false)
                .map(x -> x.getFrom())
                .distinct()
                .collect(Collectors.toList());
        return res;
    }

    public List<String> getToLocations() {
        var res = StreamSupport.stream(getAll().spliterator(), false)
                .map(x -> x.getTo())
                .distinct()
                .collect(Collectors.toList());
        return res;
    }

    public List<Flight> flightFilter(String from, String to, LocalDateTime departure) {
            Predicate<Flight> fromPredicate = f -> f.getFrom().equals(from);
            Predicate<Flight> toPredicate = f -> f.getTo().equals(to);
            Predicate<Flight> departureDay = f -> f.getDepartureTime().getDayOfMonth() == departure.getDayOfMonth();
            Predicate<Flight> departureMonth = f -> f.getDepartureTime().getMonth().equals(departure.getMonth());
            Predicate<Flight> departureYear = f -> f.getDepartureTime().getYear() == departure.getYear();
            Predicate<Flight> departurePredicate = departureDay.and(departureMonth).and(departureYear);
            var res = StreamSupport.stream(getAll().spliterator(), false)
                    .filter(fromPredicate.and(toPredicate).and(departurePredicate))
                    .collect(Collectors.toList());
            return res;
    }
}
