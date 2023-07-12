package com.juanlumn.spring.basic.service.binarysearch;

import com.juanlumn.spring.basic.service.sort.SortAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BinarySearchServiceImpl implements BinarySearchService {

    private final Logger LOG = LoggerFactory.getLogger(BinarySearchServiceImpl.class);

    @Autowired
    @Qualifier("quick")
    private final SortAlgorithm sortAlgorithm;

    public BinarySearchServiceImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    @PostConstruct
    public void postConstruct() {
        LOG.info("This is happening just after the Bean has been added to the context");
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("This is happening just before the Bean has been removed from the context");
    }

    @Override
    public int binarySearch(int[] numbers, int number) {
        // Please note this code is for Spring example purposes
        sortAlgorithm.sort(numbers);
        return Arrays.binarySearch(numbers, number);
    }
}
