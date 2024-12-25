package com.shared.info.utils.dsa;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Sorting {

    //Ascending order
    public int[] selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minVal = a[i];
            int minInd = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < minVal) {
                    minVal = a[j];
                    minInd = j;
                }
            }

            int temp = a[i];
            a[i] = a[minInd];
            a[minInd] = temp;
        }
        return a;
    }

    //Ascending order
    public int[] bubbleSortAsc(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    //Descending order
    public int[] bubbleSortDesc(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }
}
