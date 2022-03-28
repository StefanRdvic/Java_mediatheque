package persistance;

import java.util.List;

/**
 * Interface data access object
 *
 * Fait le parralele entre un object java et une table en BD
 * @param <T> l'objet � represent�
 */
public interface Dao<T> {
    T get(Object... args);

    List<T> getAll();

    void create(Object... args);

    void update(Object... args);

    void delete(int id);    //hors scope projet
}
