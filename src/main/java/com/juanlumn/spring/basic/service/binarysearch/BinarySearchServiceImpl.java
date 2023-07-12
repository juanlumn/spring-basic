package com.juanlumn.spring.basic.service.binarysearch;

import com.juanlumn.spring.basic.service.sort.SortAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BinarySearchServiceImpl implements BinarySearchService {

    @Autowired
    @Qualifier("quick")
    private final SortAlgorithm sortAlgorithm;

    public BinarySearchServiceImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    @Override
    public int binarySearch(int[] numbers, int number) {
        // Please note this code is for Spring example purposes
        sortAlgorithm.sort(numbers);
        return Arrays.binarySearch(numbers, number);
    }
}
