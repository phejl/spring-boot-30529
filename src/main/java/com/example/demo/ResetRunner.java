package com.example.demo;

import com.example.demo.data.Data;
import com.example.demo.data.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1000)
@Component
public class ResetRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetRunner.class);

    private final DataRepository dataRepository;

    public ResetRunner(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Checking");
        try {
            Data ret = dataRepository.findByUidAndUpdateAccess("abc");
            LOGGER.info("Fetched {}", ret);
        } catch (Exception ex) {
            LOGGER.error("Could not perform", ex);
        }
    }

}
