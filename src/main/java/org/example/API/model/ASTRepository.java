package org.example.API.model;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ASTRepository extends MongoRepository<ASTENTITY, ObjectId> {
    // Custom query methods (if needed)
}
