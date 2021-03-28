package service;

import domain.Flight;
import domain.Ticket;
import repository.Repository;
import utils.observer.Observable;
import utils.observer.Observer;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TicketService implements Observable {
    private Repository<Long, Ticket> repo;
    private Validator<Ticket> validator;

    public TicketService(Repository<Long, Ticket> repo, Validator<Ticket> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Iterable<Ticket> getAll(){
        return repo.findAll();
    }

    public Ticket findOne(Long ticketId) {
        return repo.findOne(ticketId);
    }

    public void addTicket(Ticket ticket) {
        repo.add(ticket);
    }

    public Long getId(){
        List<Ticket> reservations = StreamSupport.stream(getAll().spliterator(), false).collect(Collectors.toList());
        Long lastId = Long.valueOf(-100);
        for(int i = 0 ; i < reservations.size(); i++){
            Long currentId = reservations.get(i).getId();
            if(currentId > lastId)
                lastId = currentId;
        }
        return lastId + 1;
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
    public void notifyObservers() {
        observers.stream().forEach(x->x.update());
    }
}
