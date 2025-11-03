package lotto.controller;

import java.util.*;

public class Parser {
    public String[] parseWithComma(String inputString) {
        return inputString.split(",");
    }

    public int parseToInt(String rawInt) throws IllegalArgumentException {
        try {
            if (rawInt.isBlank()) {
                throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다.");
            }
            return Integer.parseInt(rawInt);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수 이외의 값은 입력할 수 없습니다.");
        }
    }

    public List<Integer> parseToIntList(String rawList) {
        if(rawList.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다.");
        }
        return Arrays.stream(rawList.split(","))
                .map(Integer::parseInt)
                .toList();
    }
}
