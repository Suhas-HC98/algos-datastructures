package com.shared.info.dsa;

import com.shared.info.utils.dsa.theoritical.NumberOfSubstitutes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NumberOfSubstitutesTest {

    private final NumberOfSubstitutes substitutes = new NumberOfSubstitutes();

    @Test
    void should_return_number_of_substitute_when_repeated_letter_is_encountered_in_any_word() {
        var input = List.of("add", "boooks", "beats");
        var response = substitutes.miniMalSubstitute(input);
        assertEquals(response.size(), input.size());
    }
}