package service;

import repository.Repository;
import vacante.domain.Client;
import vacante.domain.Hotel;
import vacante.domain.Location;
import validator.Validator;

public class HotelService {
    private Repository<Double, Hotel> repo;
    private Validator<Hotel> validator;

    public HotelService(Repository<Double, Hotel> repo, Validator<Hotel> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Iterable<Hotel> getAll(){
        return repo.findAll();
    }

    public Hotel findOne(double parseLong) {
        return repo.findOne(parseLong);
    }
}
