package com.juanlumn.spring.basic.service.binarysearch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BinarySearchServiceImplTest {
    private static final int[] NUMBERS = new int[]{2,5,1,123,6};

    @Autowired
    private BinarySearchServiceImpl binarySearchService;

    @Test
    public void binarySearch() {
        int result = binarySearchService.binarySearch(NUMBERS, 5);

        assertEquals(2, result);
    }

    @Test
    public void binarySearchNotFound() {
        int result = binarySearchService.binarySearch(NUMBERS, 4);

        assertEquals(-3, result);
    }
}