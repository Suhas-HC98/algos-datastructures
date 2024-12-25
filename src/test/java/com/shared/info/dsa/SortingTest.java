package com.shared.info.dsa;

import com.shared.info.utils.dsa.Sorting;
import org.junit.jupiter.api.Test;

import static com.shared.info.vo.TestUtils.buildSortableArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortingTest {

    private final Sorting sorting = new Sorting();

    @Test
    void should_perform_selection_sorting_when_array_is_passed() {
        var selectionSortResponse = sorting.selectionSort(buildSortableArray());
        assertEquals(buildSortableArray().length, selectionSortResponse.length);
        assertEquals(1, selectionSortResponse[0]);
    }

    @Test
    void should_perform_bubble_sort_ascending_when_array_is_passed() {
        var selectionSortResponse = sorting.bubbleSortAsc(buildSortableArray());
        assertEquals(buildSortableArray().length, selectionSortResponse.length);
        assertEquals(1, selectionSortResponse[0]);
    }

    @Test
    void should_perform_bubble_sort_descending_when_array_is_passed() {
        var selectionSortResponse = sorting.bubbleSortDesc(buildSortableArray());
        assertEquals(buildSortableArray().length, selectionSortResponse.length);
        assertEquals(5, selectionSortResponse[0]);
    }
}