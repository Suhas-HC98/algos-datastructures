package com.shared.info.dsa;

import com.shared.info.utils.dsa.theoritical.MaxDistinctProductsSell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MaxDistinctProductsSellTest {

    private final MaxDistinctProductsSell distinctProductsSell = new MaxDistinctProductsSell();

    @ParameterizedTest(name = "{index} should return {2} when {0} is passed with {1} number of picks")
    @MethodSource("minimalProductInputs")
    void should_pick_minimal_product_and_delete_so_less_products_are_left_in_the_bag(List<Integer> input, int picks,
                                                                                     int expectedOutput) {
        int response = distinctProductsSell.deleteProduct(input, picks);
        assertEquals(expectedOutput, response);
    }


    private static Stream<Arguments> minimalProductInputs() {
        return Stream.of(Arguments.of(List.of(1, 1, 1, 2, 2, 3), 2, 2),
                Arguments.of(List.of(1, 2, 2, 3), 1, 2),
                Arguments.of(List.of(1, 1, 3, 3), 2, 1));
    }
}