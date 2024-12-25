package com.shared.info.utils.dsa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
@AllArgsConstructor
public class ArrayManipulations {

    private final Sorting sorting;

    public List<Integer> sumOfTwoIsTarget(int[] arr, int target) {
        Map<Integer, Integer> checker = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int newTarget = target - arr[i];
            if (checker.containsKey(newTarget)) {
                res.add(checker.get(newTarget));
                res.add(i);
                return res;
            }
            checker.put(arr[i], i);
        }

        return res;
    }

    public List<List<Integer>> sumOfThreeIsTarget(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                int j = i + 1;
                int k = arr.length - 1;
                int newTarget = target - arr[i];
                while (j < k) {
                    if (arr[j] + arr[k] == newTarget) {
                        List<Integer> resSet = new ArrayList<>();
                        resSet.add(arr[i]);
                        resSet.add(arr[j]);
                        resSet.add(arr[k]);
                        res.add(resSet);
                        while (j < k && arr[j] == arr[j + 1]) j++;
                        while (j < k && arr[k] == arr[k - 1]) k--;
                        j++;
                        k--;
                    } else if (arr[j] + arr[k] < newTarget) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }

        return res;
    }

    public Integer smallestNumberForActualProduct(int givenNumber) {
        String answer = "";
        for (int i = 9; i > 1; i--) {
            while (givenNumber % i == 0) {
                givenNumber = givenNumber / i;
                answer = i + answer;
            }
        }

        if (givenNumber != 1) {
            return -1;
        } else {
            return Integer.parseInt(answer);
        }
    }

    public int[] separateEvenAndOdd(int[] a) {
        int i = 0;
        int j = a.length - 1;

        while (i < j) {
            while (i < j && a[j] % 2 != 0)
                j--;
            while (i < j && a[i] % 2 == 0)
                i++;

            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }

        return a;
    }

    public List<Integer> extractArrayElementsSpecifically(int[] a) {
        List<Integer> response = new ArrayList<>();

        for (int number : a) {
            if (String.valueOf(number).startsWith("2"))
                response.add(number);
        }

        return response;
    }

    public Map<Integer, Integer> checkOccurrences(int[] a) {
        Map<Integer, Integer> response = new LinkedHashMap<>();
        for (int number : a) {
            if (response.containsKey(number)) {
                response.put(number, response.get(number) + 1);
            } else {
                response.put(number, 1);
            }
        }

        return response;
    }

    public void printSpherically(int[][] a) {
        int rowMin = 0, rowMax = a[0].length - 1, colMin = 0, colMax = a.length - 1, n = a.length, m = a[0].length, count = 0;

        while (count < (n * m)) {
            //top boundary
            for (int row = colMin; row <= colMax && count < (n * m); row++) {
                System.out.print(a[rowMin][row] + " ");
                count++;
            }
            rowMin++;
            //right boundary
            for (int col = rowMin; col <= rowMax && count < (n * m); col++) {
                System.out.print(a[col][colMax] + " ");
                count++;
            }
            colMax--;
            //bottom boundary
            for (int row = colMax; row >= colMin && count < (n * m); row--) {
                System.out.print(a[rowMax][row] + " ");
                count++;
            }
            rowMax--;
            //left boundary
            for (int col = rowMax; col >= rowMin && count < (n * m); col--) {
                System.out.print(a[col][colMin] + " ");
                count++;
            }
            colMin++;
        }
    }

    public int averageOfArray(int[] a) {
        int avg = -1;
        for (int i : a) {
            avg += i;
        }

        return avg / a.length;
    }

    public int secondLargestElementInArray(int[] a) {
        int[] selectionSortedArray = sorting.selectionSort(a);
        return selectionSortedArray[selectionSortedArray.length - 2];
    }


    public int secondSmallestElementInArray(int[] a) {
        int[] bubbleSortedArray = sorting.bubbleSortAsc(a);
        return bubbleSortedArray[1];
    }

    public int evenElementsOfArray(int[] a) {
        int count = 0;
        for (int i : a) {
            if (i % 2 == 0 || (i & 1) == 0) {
                count++;
            }
        }

        return count;
    }

    public int oddElementsOfArray(int[] a) {
        int count = 0;
        for (int i : a) {
            if (i % 2 != 0 || (i & 1) != 0) {
                count++;
            }
        }

        return count;
    }

    public int[] reverse(int[] a) {
        int i = 0;
        int j = a.length - 1;

        while (i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            i++;
            j--;
        }

        return a;
    }

    public int[] duplicate(int[] a) {
        Set<Integer> response = new LinkedHashSet<>();
        for (int i : a) {
            response.add(i);
        }

        return response.stream().mapToInt(Integer::intValue).toArray();
    }

    public Map<Integer, Integer> frequencyOfArrayElements(int[] a) {
        Map<Integer, Integer> res = new HashMap<>();
        for (int number : a) {
            if (res.containsKey(number)) {
                res.put(number, res.get(number) + 1);
            } else {
                res.put(number, 1);
            }
        }

        return res;
    }

    public int[] rightShift(int[] a) {
        int lastElement = a[a.length - 1];
        for (int i = a.length - 1; i > 0; i--) {
            a[i] = a[i - 1];
        }
        a[0] = lastElement;

        return a;
    }

    public int[] rightShiftNTimes(int[] a, int n) {
        int[] response = new int[10];
        for (int i = 0; i <= n; i++) {
            response = rightShift(a);
        }

        return response;
    }

    public int[] leftShift(int[] a) {
        int firstElement = a[0];
        for (int i = 0; i < a.length - 1; i++) {
            a[i] = a[i + 1];
        }
        a[a.length - 1] = firstElement;

        return a;
    }

    public int[] leftShiftNTimes(int[] a, int n) {
        int[] response = new int[10];
        for (int i = 0; i <= n; i++) {
            response = leftShift(a);
        }

        return response;
    }

    public int[] copyArray(int[] a, int[] b) {
        b = a.clone();
        //b = a;

        return b;
    }

    public boolean checkArrayEqualization(int[] a, int[] b) {
        boolean response = false;
        if (a.length == b.length) {
            response = true;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                response = false;
            }
        }

        return response;
    }

    public boolean checkAscendingSort(int[] a) {
        boolean isAscendingSorted = false;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1]) {
                isAscendingSorted = true;
            }
        }

        return isAscendingSorted;
    }

    public boolean checkDescendingSort(int[] a) {
        boolean isDescendingSorted = true;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1]) {
                isDescendingSorted = false;
            }
        }

        return isDescendingSorted;
    }

    public int firstNonRepeatedElement(int[] a) {
        Map<Integer, Integer> frequency = frequencyOfArrayElements(a);
        for (Map.Entry<Integer, Integer> map : frequency.entrySet()) {
            if (map.getValue() == 1) {
                return map.getKey();
            }
        }

        return 0;
    }

    public int mode(int[] a) {
        Map<Integer, Integer> frequency = frequencyOfArrayElements(a);
        int mode;
        int freq = 1;
        for (Map.Entry<Integer, Integer> map : frequency.entrySet()) {

            if (map.getValue() > freq) {
                mode = map.getKey();
                return mode;
            }
        }

        return 0;
    }

    public int range(int[] a) {
        int min = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            } else if (a[i] < min) {
                min = a[i];
            }
        }

        return (min + max) / 2;
    }

    public int diffMaxNMin(int[] a) {
        int min = a[0];
        int max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            } else if (a[i] < min) {
                min = a[i];
            }
        }

        return max - min;
    }

    public int avrEvenElements(int[] a) {
        int count = 0, even = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                count++;
                even += a[i];
            }
        }

        return even / count;
    }

    public int avrOddElements(int[] a) {
        int count = 0, odd = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 != 0) {
                count++;
                odd += a[i];
            }
        }

        return odd / count;
    }

    public int productOfAllElements(int[] a) {
        int product = 0;
        for (int i = 0; i < a.length; i++) {
            product *= a[i];
        }

        return product;
    }
}
