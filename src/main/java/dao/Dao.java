package dao;

import java.util.List;

public interface Dao<T> {

    T getByID(int id);

    List<T> getAll();

    void add(T t);

    void update(T t);

    void delete(T t);
}
