package socialnetwork.repository;

import socialnetwork.domain.Entity;

import java.io.IOException;

public interface Repository<ID, E extends Entity<ID>> {

    E findOne(ID id);

    Iterable<E> findAll();

    E add(E entity);

    E delete(ID id) throws IOException;
}
