package socialnetwork.repository.memory;

import socialnetwork.domain.Entity;
import socialnetwork.repository.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    Map<ID, E> entities;

    public InMemoryRepository() {
        entities = new HashMap<ID, E>();
    }

    @Override
    public E findOne(ID id) {
        if (id==null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E add(E entity) {
       if(entity == null)
           throw new IllegalArgumentException("entity must be not null");
       if(entities.get(entity.getID()) != null)
           return entity;
       else entities.put(entity.getID(), entity);
       return null;
    }

    @Override
    public E delete(ID id) throws IOException {
        if(id == null)
            throw new IllegalArgumentException("id must be not null");
        if(entities.get(id) == null){
            throw new IllegalArgumentException("no entity with this id");
        }
        else{
            E entity = entities.get(id);
            entities.remove(id);
            return entity;
        }
    }

    public int count(){
        return entities.size();
    }

}
