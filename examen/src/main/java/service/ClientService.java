package service;

import domain.Client;
import repository.Repository;
import validator.Validator;

public class ClientService {
    private Repository<String, Client> repo;
    private Validator<Client> validator;

    public ClientService(Repository<String, Client> repo, Validator<Client> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Iterable<Client> getAll(){
        return repo.findAll();
    }

    public Client findOne(String username) {
        return repo.findOne(username);
    }

    public Client login(String username) {
        return findOne(username);
    }
}
