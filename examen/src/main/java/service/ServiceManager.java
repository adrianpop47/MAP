package service;

import domain.Flight;
import domain.Ticket;
import utils.observer.Observable;
import utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager implements Observable {
    private ClientService clientService;
    private FlightService flightService;
    private TicketService ticketService;

    public ServiceManager(ClientService clientService, FlightService serviceFlight, TicketService ticketService) {
        this.clientService = clientService;
        this.flightService = serviceFlight;
        this.ticketService = ticketService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public FlightService getFlightService() {
        return flightService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void addTicket(Ticket ticket) {
        ticketService.addTicket(ticket);
        Integer seats = flightService.findOne(ticket.getFlightId()).getSeats();
        flightService.findOne(ticket.getFlightId()).setSeats(seats - 1);
        notifyObservers();
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
