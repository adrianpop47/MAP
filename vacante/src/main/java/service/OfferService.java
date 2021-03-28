package service;

import repository.Repository;
import vacante.domain.Location;
import vacante.domain.SpecialOffer;
import validator.Validator;

public class OfferService {
    private Repository<Double, SpecialOffer> repo;
    private Validator<SpecialOffer> validator;

    public OfferService(Repository<Double, SpecialOffer> repo, Validator<SpecialOffer> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Iterable<SpecialOffer> getAll(){
        return repo.findAll();
    }

    public SpecialOffer findOne(double parseLong) {
        return repo.findOne(parseLong);
    }
}
