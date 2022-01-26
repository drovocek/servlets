package edu.volkov.mvc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<ID, T> {

    List<T> findAll() throws SQLException;

    Optional<T> findById(ID id);

    boolean delete(ID id);

    void update(T persist);

    T save(T persist);
}
