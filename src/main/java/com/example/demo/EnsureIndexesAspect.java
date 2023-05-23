package com.example.demo;

import com.example.demo.data.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EnsureIndexesAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnsureIndexesAspect.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoConverter mongoConverter;

    private boolean indexesCreated;

    @Pointcut("execution(* com.example.demo.data.DataRepository.*(..))")
    public void repositoryMethod() {
    }

    @Around("repositoryMethod()")
    public Object ensureIndexes(final ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.info("Aspect invoked");
        ensureIndexes();
        return pjp.proceed(pjp.getArgs());
    }

    synchronized void ensureIndexes() {
        if (!indexesCreated) {
            IndexOperations indexOps = mongoTemplate.indexOps(Data.class);
            IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoConverter.getMappingContext());
            Data.getIndexes(resolver).forEach(indexOps::ensureIndex);
            LOGGER.info("Indexes initialized");
            indexesCreated = true;
        }
    }
}
