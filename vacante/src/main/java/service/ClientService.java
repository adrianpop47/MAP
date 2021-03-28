package service;

import repository.Repository;
import vacante.domain.Client;
import vacante.domain.Hotel;
import validator.Validator;

public class ClientService {
    private Repository<Long, Client> repo;
    private Validator<Client> validator;

    public ClientService(Repository<Long, Client> repo, Validator<Client> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Iterable<Client> getAll(){
        return repo.findAll();
    }

    public Client findOne(long parseLong) {
        return repo.findOne(parseLong);
    }
}
