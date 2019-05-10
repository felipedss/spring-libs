package com.felipedsr.springlibsmongodbrepository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;

import java.io.Serializable;
import java.util.Collection;

/**
 * Repository with basic operations using QueryDsl e MongoDb.
 */
public class BasicRepository implements Repository {

    private MongoOperations mongoOperations;

    /**
     * Find the entities
     *
     * @param entityClass entity class to be located
     * @param <T>         type of entities to be located
     * @return an ordered list containing all localized entities
     */
    @Override
    public <T> Collection<T> findAll(Class<T> entityClass) {
        return query(entityClass).fetch();
    }

    /**
     * Find an entity by type and id
     *
     * @param entityClass entity class to be located
     * @param id          entity id to be located
     * @param <T>         entity type
     * @return the entity that has the type and id provided
     */
    public <T> T findById(Class<T> entityClass, Serializable id) {
        return mongoOperations.findById(id, entityClass);
    }


    /**
     * Execute a custom search
     *
     * @param <T>         entity type
     * @param entityClass class of entity
     * @return search result
     */
    private <T> SpringDataMongodbQuery<T> query(Class<T> entityClass) {
        return new SpringDataMongodbQuery<>(mongoOperations, entityClass);
    }


}