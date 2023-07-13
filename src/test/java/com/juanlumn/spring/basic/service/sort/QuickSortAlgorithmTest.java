package com.juanlumn.spring.basic.service.sort;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuickSortAlgorithmTest {

    @Autowired
    private QuickSortAlgorithm quickSortAlgorithm;
    @Test
    public void sort() {
        int[] numbers = new int[]{2,5,1,123,6};

        assertEquals(2, numbers[0]);
        assertEquals(6, numbers[4]);

        quickSortAlgorithm.sort(numbers);

        assertArrayEquals(new int[]{1,2,5,6,123}, numbers);
    }
}