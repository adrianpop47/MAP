package socialnetwork.service;

import socialnetwork.domain.CererePrietenie;
import socialnetwork.domain.Message;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository;
import socialnetwork.utils.IdProvider;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CereriService implements Observable {
    private Repository<Long, CererePrietenie> repo;
    private IdProvider idProvider;

    public CereriService(Repository<Long, CererePrietenie> repo) {
        this.repo = repo;
        this.idProvider = new IdProvider(this.lastId());
    }

    public Iterable<CererePrietenie> getAll(){
        return repo.findAll();
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

    public CererePrietenie sendFriendRequest(CererePrietenie cererePrietenie) {
        for(CererePrietenie c : getAll())
        {
            if(c.getU1() == cererePrietenie.getU1() && c.getU2()== cererePrietenie.getU2()){
                throw new IllegalArgumentException("Aceasta cerere exista deja!");
            }
            if(c.getU1() == cererePrietenie.getU2() && c.getU2()== cererePrietenie.getU1()){
                throw new IllegalArgumentException("Aceasta cerere exista deja!");
            }
        }
        cererePrietenie.setId(idProvider.getUniqueId());
        CererePrietenie p = repo.add(cererePrietenie);
        notifyObservers();
        return p;
    }

    public CererePrietenie findOne(Long id){return repo.findOne(id);}

    public void respondFriendRequest(CererePrietenie cererePrietenie, String status) throws IOException {
        cererePrietenie.setStatus(status);
        repo.delete(cererePrietenie.getID());
        repo.add(cererePrietenie);
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

    public CererePrietenie deleteCerere(Long id) throws IOException {
        CererePrietenie task = repo.delete(id);
        notifyObservers();
        return task;
    }
}
