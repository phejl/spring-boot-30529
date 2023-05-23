package com.example.demo.data;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<Data, ObjectId>, DataRepositoryCustom {

    Data findByUid(String uid);

}
