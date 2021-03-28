package socialnetwork.service;

import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository;
import socialnetwork.utils.IdProvider;
import socialnetwork.validator.Validator;

import java.io.IOException;

public class UtilizatorService  {
    private Validator<Utilizator> validator;
    private Repository<Long, Utilizator> repo;
    private IdProvider idProvider;


    public UtilizatorService(Repository<Long, Utilizator> repo, Validator<Utilizator> validator) {
        this.validator = validator;
        this.repo = repo;
        this.idProvider = new IdProvider(this.lastId());
    }

    public Utilizator addUtilizator(Utilizator messageTask) {
        messageTask.setId(idProvider.getUniqueId());
        validator.validate(messageTask);
        Utilizator task = repo.add(messageTask);
        if(task != null)
            throw new IllegalArgumentException("Id deja existent!");
        return task;
    }

    public Iterable<Utilizator> getAll(){
        return repo.findAll();
    }

    public Utilizator deleteUtilizator(Long id) throws Exception {
        Utilizator task = repo.delete(id);
        return task;
    }

    boolean findId(long id)
    {
        if(repo.findOne(id) != null)
            return true;
        return false;
    }

    private long lastId()
    {
        final long[] id = {-1};
        this.getAll().forEach(x->
        {
            if(x.getID() > id[0])
                id[0] = x.getID();
        });
        return id[0];
    }

    public long getNextId(){return idProvider.getNextId();}

    public Utilizator findOne(Long id){return repo.findOne(id);}
}
