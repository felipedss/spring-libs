package com.felipedsr.springlibsmongodbrepository;

import java.io.Serializable;
import java.util.Collection;

public interface Repository {

    <T> Collection<T> findAll(Class<T> entityClass);

    <T> T findById(Class<T> entityClass, Serializable id);

}
