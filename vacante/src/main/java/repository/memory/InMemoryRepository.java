package repository.memory;

import repository.Repository;
import vacante.domain.Entity;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    Map<ID, E> entities;

    public InMemoryRepository() {
        entities = new HashMap<ID, E>();
    }

    @Override
    public E findOne(ID id) {
        if(id == null)
            throw new IllegalArgumentException("id must not be null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E add(E entity) {
        if(entity == null)
            throw new IllegalArgumentException("entity must not be null");
        if(entities.get(entity.getId()) != null)
            return entity;
        else entities.put(entity.getId(), entity);
        return null;
    }
}
