package service;

import vacante.domain.Client;
import vacante.domain.Hotel;
import vacante.domain.OfferDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceManager {
    private LocationService locationService;
    private HotelService hotelService;
    private OfferService offerService;
    private ClientService clientService;
    private ReservationService reservationService;

    public ServiceManager(LocationService locationService, HotelService hotelService, OfferService offerService,
                          ClientService clientService, ReservationService reservationService) {
        this.locationService = locationService;
        this.hotelService = hotelService;
        this.offerService = offerService;
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public OfferService getOfferService() {
        return offerService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }

    public List<OfferDto> offerDtoList(Long clientId) {
        Client client = clientService.findOne(clientId);
        List<OfferDto> rez = StreamSupport
                .stream(offerService.getAll().spliterator(), false)
                .filter(o -> o.getPercents() < client.getFidelityGrade())
                .filter(o -> o.getEndDate().after(new Date()))
                .map(o -> new OfferDto(getHotelService().findOne(o.getHotelId()).getHotelName(),
                        getLocationService().findOne(getHotelService().findOne(o.getHotelId()).getLocationId()).getLocationName(),
                        o.getStartDate(), o.getEndDate()))
                .collect(Collectors.toList());
        return rez;
    }
}
