package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public final class LottoRules {
    private static final int LOTTO_LENGTH = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public static void validateNumber(int number) throws IllegalArgumentException {
        if(number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 1~45 사이의 정수만 입력하세요.");
        }
    }

    public static void validateRangeOfLotto(List<Integer> numbers) {
        for(Integer number: numbers) {
            if(number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 1~45 사이의 정수만 입력하세요.");
            }
        }
    }

    public static void validateMoney(int money) throws IllegalArgumentException {
        if(money <= 0) {
            throw new IllegalArgumentException("[ERROR] 정수 이외의 값은 입력할 수 없습니다.");
        }
        if(money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000원 단위로 입력해주세요.");
        }
    }

    public static void validateDuplication(List<Integer> numbers) throws IllegalArgumentException {
        Set<Integer> convertedNumbers = new HashSet<>(numbers);
        if(numbers.size() != convertedNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복숫자는 입력할 수 없습니다.");
        }
    }

    public static void validateDuplicateBonus(List<Integer> numbers, int bonusNumber) throws IllegalArgumentException {
        if(numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
