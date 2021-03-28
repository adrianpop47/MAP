package service;

import repository.Repository;
import vacante.domain.Hotel;
import vacante.domain.Location;
import validator.Validator;

public class LocationService {
    private Repository<Double, Location> repo;
    private Validator<Location> validator;

    public LocationService(Repository<Double, Location> repo, Validator<Location> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Iterable<Location> getAll(){
        return repo.findAll();
    }

    public Location findOne(double parseLong) {
        return repo.findOne(parseLong);
    }
}
