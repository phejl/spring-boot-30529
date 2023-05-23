package com.example.demo.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexDefinition;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.PartialIndexFilter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;

@Document
public final class Data {

    @Id
    private ObjectId id;

    private String uid;

    public Data() {
        super();
    }

    public Data(String uid) {
        this.uid = uid;
    }

    public static List<IndexDefinition> getIndexes(IndexResolver indexResolver) {
        List<IndexDefinition> ret = new ArrayList<>();
        indexResolver.resolveIndexFor(Data.class).forEach(ret::add);

        ret.add(new Index()
                .unique()
                .on("uid", Sort.Direction.ASC)
                .partial(PartialIndexFilter.of(Criteria.where("uid").exists(true))));

        return ret;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
