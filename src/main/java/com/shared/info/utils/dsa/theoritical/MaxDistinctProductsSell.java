package com.shared.info.utils.dsa.theoritical;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MaxDistinctProductsSell {

    public int deleteProduct(List<Integer> ids, int m) {
        log.info("Input received {}", ids);

        Map<Integer, Integer> frequencyMap = new LinkedHashMap<>();

        for (int id : ids) {
            frequencyMap.put(id, frequencyMap.getOrDefault(id, 0) + 1);
        }

        log.info("frequencies {}", frequencyMap);
        List<Integer> frequencyItems = new ArrayList<>(frequencyMap.values());
        log.info("list containing frequency values {}", frequencyItems);
        Collections.sort(frequencyItems);
        log.info("sorted list {}", frequencyItems);

        for (int i = 0; i < frequencyItems.size() && m > 0; i++) {
            if (frequencyItems.get(i) <= m) {
                m = m - frequencyItems.get(i);
                frequencyItems.set(i, 0);
            } else {
                frequencyItems.set(i, frequencyItems.get(i) - m);
                m = 0;
            }
        }
        int distinctItems = 0;

        for (Integer items : frequencyItems) {
            if (items > 0) distinctItems++;
        }
        return distinctItems;
    }
}
