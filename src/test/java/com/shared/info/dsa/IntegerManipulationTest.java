package com.shared.info.dsa;

import com.shared.info.utils.dsa.IntegerManipulation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class IntegerManipulationTest {

    @InjectMocks
    IntegerManipulation integerManipulation;

    @Test
    void printSquareRootsTill() {
        var response =
                integerManipulation.printSquareRootsTill(20);

        assertEquals(16, response.get(response.size() - 1));
    }

    @ParameterizedTest(name = "{index} - Given Number {0}")
    @ValueSource(ints = {13, 47, 71, 89})
    void check_whether_number_is_odd(int number) {
        assertTrue(integerManipulation.isOdd(number));
    }


    @ParameterizedTest(name = "Provided Input {0}")
    @NullAndEmptySource
    @ValueSource(strings = {""})
    void check_whether_give_word_is_empty(String word) {
        assertTrue(integerManipulation.isEmpty(word));
    }

    @ParameterizedTest(name = "Provided Input {0}")
    @NullSource
    void check_whether_give_word_is_null(String word) {
        assertTrue(isNull(word));
    }
}