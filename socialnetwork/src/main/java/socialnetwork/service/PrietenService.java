package socialnetwork.service;

import javafx.beans.InvalidationListener;
import socialnetwork.domain.CererePrietenie;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository;
import socialnetwork.utils.Graph;
import socialnetwork.utils.IdProvider;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;
import socialnetwork.validator.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrietenService implements Observable {
    private Repository<Long, Prietenie> repo;
    private Validator<Prietenie> validator;
    private IdProvider idProvider;


    public PrietenService(Repository<Long, Prietenie> repo, Validator<Prietenie> validator) {
        this.repo = repo;
        this.validator = validator;
        this.idProvider = new IdProvider(this.lastId());
    }

    public Prietenie addPrietenie(Prietenie messageTask) {
        messageTask.setId(idProvider.getUniqueId());
        validator.validate(messageTask);
        Prietenie task = null;
        if(findFriendship(messageTask) == false)
        {
            task = repo.add(messageTask);
            if(task != null)
                throw new IllegalArgumentException("Id deja existent!");
                notifyObservers();
        }
        return task;
    }

    public Prietenie deletePrietenie(Long id) throws Exception {
        Prietenie task = repo.delete(id);
        notifyObservers();
        return task;
    }

    public Iterable<Prietenie> getAll(){
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

    private boolean findFriendship(Prietenie prietenie)
    {
        this.getAll().forEach(x->{
            if(x.getU1() == prietenie.getU1() && x.getU2() == prietenie.getU2())
            {
               throw new IllegalArgumentException("Prietenie deja existenta!");
            }
            if(x.getU1() == prietenie.getU2() && x.getU2()== prietenie.getU1())
            {
                throw new IllegalArgumentException("Prietenie deja existenta!");
            }
        });
        return false;
    }


    private Graph formeazaGraf(long size){
        int intSize = (int)size;
        Graph g = new Graph(intSize);
        for(Prietenie p : getAll())
        {
            int sursa = p.getU1().intValue();
            int destinatie = p.getU2().intValue();
            g.addEdge(sursa, destinatie);
            g.addEdge(destinatie, sursa);
        }
        return g;
    }

    public int numarComunitati(long id){
        Graph g = formeazaGraf(id);
        return g.componenteConexe();
    }


    public int[] comunitateMaxima(long id){
        Graph g = formeazaGraf(id);
        return g.comunitateMaxima();
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

    public Prietenie findOne(Long id){return repo.findOne(id);}
}
