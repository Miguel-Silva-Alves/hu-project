package br.ifsp.husaocarlos.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO <K,T>{
    boolean save(T object);
    Optional<T> findOne(K key);
    List<T> findAll();
    boolean update(T object);
    boolean delete(K key);
}
