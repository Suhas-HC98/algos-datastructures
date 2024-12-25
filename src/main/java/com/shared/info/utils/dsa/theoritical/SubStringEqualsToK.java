package com.shared.info.utils.dsa.theoritical;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SubStringEqualsToK {

    public String smallestSubStringEqualsK(List<String> str, int k) {
        List<String> matchingStrings = new ArrayList<>(str.size());
        for (String s : str) {
            char[] arr = s.toCharArray();
            int count = 0;
            for (char c : arr) if (c == '1') count++;
            if (count == k) matchingStrings.add(s);
        }
        return matchingStrings.stream()
                .min(Comparator.comparing(String::length))
                .orElse(null);
    }
}
