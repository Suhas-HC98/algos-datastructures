package com.shared.info.dsa;

import com.shared.info.utils.dsa.ArrayManipulations;
import com.shared.info.utils.dsa.Sorting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArrayManipulationsTest {

    @InjectMocks
    private ArrayManipulations arrayManipulations;

    @Mock
    private Sorting sorting;

    @Test
    @DisplayName("Summation of 2 array elements is target")
    void sumOfTwoIsTargetArray() {
        int[] inputArray = {2, 4, 3, 1, 6};
        int target = 7;
        var expectedResult = Arrays.asList(1, 2);

        var response = arrayManipulations.sumOfTwoIsTarget(inputArray, target);
        assertEquals(expectedResult, response);
    }

    @Test
    @DisplayName("Summation of 3 array elements is target")
    void sumOfThreeIsTargetArray() {
        int[] inputArray = {2, 4, 1, 3, 1, 2, 4, 1, 1, 3, 2};
        int target = 7;
        var expectedResult = Arrays.asList(2, 4, 1);

        var response = arrayManipulations.sumOfThreeIsTarget(inputArray, target);
        assertEquals(expectedResult, response.get(0));
    }

    @Test
    @DisplayName("Smallest numbers whose digits product to give actual number")
    void smallestNumberForProduct() {
        int number = 1000;
        var response = arrayManipulations.smallestNumberForActualProduct(number);
        assertEquals(5558, response);
    }

    @Test
    @DisplayName("Print a 2D matrix spherically")
    void printMatrixSpherically() {
        int[][] inputArray = {{2, 4, 1, 3},
                {3, 1, 2, 7},
                {4, 1, 1, 3},
                {5, 9, 8, 0}};
        var outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        arrayManipulations.printSpherically(inputArray);

        var expectedOutput = "2 4 1 3 7 3 0 8 9 5 4 3 1 2 1 1";
        var actualOutput = outContent.toString().replaceAll("\\r\\n|\\r|\\n", " ").trim();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @DisplayName("Separating Even And Odd Digits from array")
    void separateEvenAndOdd() {
        int[] expectedResponse = {4, 4, 6, 8, 2, 1, 7, 3};
        int[] response = arrayManipulations.separateEvenAndOdd(array());
        assertEquals(expectedResponse[0], response[0]);
    }

    @Test
    @DisplayName("Extracting digits starting with 2 from the array")
    void extractNumberFromArray() {
        var response = arrayManipulations.extractArrayElementsSpecifically(array());
        assertEquals(2, response.get(0));
    }

    @Test
    @DisplayName("Checks the occurrences of array elements")
    void checkOccurrences() {
        var response = arrayManipulations.checkOccurrences(array());
        assertEquals(2, response.get(4));
    }

    @Test
    @DisplayName("Checks average of array elements")
    void avg() {
        int response = arrayManipulations.averageOfArray(array());
        assertEquals(4, response);
    }

    @Test
    @DisplayName("Second largest array element")
    void secondLargest() {
        when(sorting.selectionSort(any()))
                .thenReturn(new int[]{1, 2, 3, 4, 4, 6, 7, 8});

        int response = arrayManipulations.secondLargestElementInArray(array());
        assertEquals(7, response);
    }

    @Test
    @DisplayName("Second smallest array element")
    void secondSmallest() {
        when(sorting.bubbleSortAsc(any()))
                .thenReturn(new int[]{1, 2, 3, 4, 4, 6, 7, 8});

        int response = arrayManipulations.secondSmallestElementInArray(array());
        assertEquals(2, response);
    }

    @Test
    @DisplayName("Count of Even array element")
    void evenElements() {
        int response = arrayManipulations.evenElementsOfArray(array());
        assertEquals(5, response);
    }

    @Test
    @DisplayName("Count of Odd array element")
    void oddElements() {
        int response = arrayManipulations.oddElementsOfArray(array());
        assertEquals(3, response);
    }

    @Test
    @DisplayName("Reverse array element")
    void reverse() {
        int[] response = arrayManipulations.reverse(array());
        assertEquals(4, response[0]);
    }

    @Test
    @DisplayName("Duplicate removal from array element")
    void duplicate() {
        int[] response = arrayManipulations.duplicate(array());
        assertEquals(array().length - 1, response.length);
    }

    @Test
    @DisplayName("Frequency of array element")
    void frequency() {
        var response = arrayManipulations.frequencyOfArrayElements(array());
        assertEquals(2, response.get(4));
    }

    @Test
    @DisplayName("Right shift array element")
    void rightShift() {
        int[] response = arrayManipulations.rightShift(array());
        assertEquals(7, response[response.length - 1]);
    }

    @Test
    @DisplayName("Right shift array element for N times")
    void rightShiftNTimes() {
        int[] response = arrayManipulations.rightShiftNTimes(array(), 1);
        assertEquals(7, response[0]);
    }

    @Test
    @DisplayName("Left shift array element")
    void leftShift() {
        int[] response = arrayManipulations.leftShift(array());
        assertEquals(3, response[response.length - 1]);
    }

    @Test
    @DisplayName("Left shift array element for N times")
    void leftShiftNTimes() {
        int[] response = arrayManipulations.leftShiftNTimes(array(), 1);
        assertEquals(6, response[0]);
    }

    @Test
    @DisplayName("Copy array element to a new one")
    void copy() {
        int[] b = new int[10];
        int[] response = arrayManipulations.copyArray(array(), b);
        assertEquals(array()[0], response[0]);
    }

    @Test
    @DisplayName("Array element comparison")
    void equals() {
        int[] b = array().clone();
        boolean response = arrayManipulations.checkArrayEqualization(array(), b);
        assertTrue(response);
    }

    @Test
    @DisplayName("Array ascending ly sorted")
    void isAscendingSorted() {
        Arrays.sort(array());
        boolean response = arrayManipulations.checkAscendingSort(array());
        assertTrue(response);
    }

    @Test
    @DisplayName("Array descending ly sorted")
    void isDescendingSorted() {
        boolean response = arrayManipulations.checkDescendingSort(array());
        assertFalse(response);
    }

    @Test
    @DisplayName("Non-repeated array element")
    void nonRepeatedArrayElement() {
        int response = arrayManipulations.firstNonRepeatedElement(array());
        assertEquals(1, response);
    }

    @Test
    @DisplayName("Mode of array element")
    void mode() {
        int response = arrayManipulations.mode(array());
        assertEquals(4, response);
    }

    @Test
    @DisplayName("Mode of array element")
    void range() {
        int response = arrayManipulations.range(array());
        assertEquals(4, response);
    }

    @Test
    @DisplayName("Difference between maximum and minimum elements")
    void diff() {
        int response = arrayManipulations.diffMaxNMin(array());
        assertEquals(7, response);
    }

    @Test
    @DisplayName("Average of even elements in an array")
    void avgEven() {
        int response = arrayManipulations.avrEvenElements(array());
        assertEquals(4, response);
    }

    @Test
    @DisplayName("Average of odd elements in an array")
    void avgOdd() {
        int response = arrayManipulations.avrOddElements(array());
        assertEquals(3, response);
    }

    private int[] array() {
        return new int[]{3, 4, 6, 1, 2, 8, 7, 4};
    }
}