package com.shared.info.dsa;

import com.shared.info.utils.dsa.theoritical.SubStringEqualsToK;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SubStringEqualsToKTest {

    private final SubStringEqualsToK service = new SubStringEqualsToK();

    @Test
    void should_return_smallest_substring_that_matches_k_when_valid_list_is_passed() {
        var input = List.of("1101", "010011", "111", "011");
        var response = service.smallestSubStringEqualsK(input, 3);

        assertNotNull(response);
        assertEquals(input.get(2), response);
    }
}