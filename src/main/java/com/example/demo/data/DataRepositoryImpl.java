package com.example.demo.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class DataRepositoryImpl implements DataRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public DataRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Data findByUidAndUpdateAccess(String uid) {
        Query query = new Query().addCriteria(Criteria
                .where("uid").is(uid));
        return mongoTemplate.findOne(query, Data.class);
    }

}
