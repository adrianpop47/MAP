package service;

import repository.Repository;
import utils.observer.Observable;
import utils.observer.Observer;
import vacante.domain.Reservation;
import vacante.domain.SpecialOffer;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReservationService implements Observable<Reservation> {
    private Repository<Double, Reservation> repo;
    private Validator<Reservation> validator;

    public ReservationService(Repository<Double, Reservation> repo, Validator<Reservation> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Iterable<Reservation> getAll(){
        return repo.findAll();
    }

    public Reservation findOne(double parseLong) {
        return repo.findOne(parseLong);
    }

    public Double getId(){
        List<Reservation> reservations = StreamSupport.stream(getAll().spliterator(), false).collect(Collectors.toList());
        Double lastId = -100.0;
        for(int i = 0 ; i < reservations.size(); i++){
            Double currentId = reservations.get(i).getId();
            if(currentId > lastId)
                lastId = currentId;
        }
        return lastId + 1;
    }


    public void addReservation(Reservation reservation) {
        repo.add(reservation);
        notifyObservers(reservation);
    }

    private List<Observer> observers=new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Reservation reservation) {
        observers.stream().forEach(x->x.update(reservation));
    }
}

