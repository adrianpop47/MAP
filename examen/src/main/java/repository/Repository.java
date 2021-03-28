package repository;

import domain.Entity;

public interface Repository<ID, E extends Entity<ID>> {
    E findOne(ID id);

    Iterable<E> findAll();

    E add(E entity);
}
