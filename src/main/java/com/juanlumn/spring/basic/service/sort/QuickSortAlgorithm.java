package com.juanlumn.spring.basic.service.sort;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Qualifier("quick")
public class QuickSortAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] numbers) {
        // Please note this code is for Spring example purposes
        Arrays.sort(numbers);
    }
}
