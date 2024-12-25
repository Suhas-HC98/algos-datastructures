package com.shared.info.utils.dsa.theoritical;

import java.util.ArrayList;
import java.util.List;

public class NumberOfSubstitutes {

    public List<Integer> miniMalSubstitute(List<String> str) {
        List<Integer> result = new ArrayList<>(str.size());
        for (String word : str) {
            char[] chars = word.toCharArray();
            int changes = 0;

            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] == chars[i + 1]) {
                    changes++;
                    i++;
                }
            }
            result.add(changes);
        }

        return result;
    }
}
