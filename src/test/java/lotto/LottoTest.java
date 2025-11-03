package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.WinningLotto;
import lotto.controller.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 아니면 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 중복숫자는 입력할 수 없습니다.");
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 로또_번호_범위_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 1~45 사이의 정수만 입력하세요.");

        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 45)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 1~45 사이의 정수만 입력하세요.");
    }
}

/**
 * WinningLotto 도메인 객체의 유효성 (특히 보너스 번호)을 검증하는 테스트
 */
class WinningLottoTest {

    private final List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 보너스번호_범위_예외() {
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 1~45 사이의 정수만 입력하세요.");

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 1~45 사이의 정수만 입력하세요.");
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스번호_중복_예외() {
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}

class LottoMachineTest {
    @DisplayName("구매 금액이 1,000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 구매금액_1000원단위_예외() {
        assertThatThrownBy(() -> new LottoMachine(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 1,000원 단위로 입력해주세요.");
    }

    @DisplayName("구매 금액이 1,000원 미만이면 예외가 발생한다.")
    @Test
    void 구매금액_1000원미만_예외() {
        // 1000원 미만도 1000원 단위가 아니므로 동일한 예외가 발생해야 함
        assertThatThrownBy(() -> new LottoMachine(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 1,000원 단위로 입력해주세요.");
    }
}

class ParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    // --- parseToInt (구매 금액, 보너스 번호용) 테스트 ---

    @DisplayName("[parseToInt] 빈 값을 입력하면 [ERROR] 빈 값은 입력할 수 없습니다. 예외가 발생한다.")
    @Test
    void parseToInt_공백_예외() {
        assertThatThrownBy(() -> parser.parseToInt(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 빈 값은 입력할 수 없습니다.");

        assertThatThrownBy(() -> parser.parseToInt(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 빈 값은 입력할 수 없습니다.");
    }

    @DisplayName("[parseToInt] 정수가 아닌 값을 입력하면 [ERROR] 정수 이외의 값은 입력할 수 없습니다. 예외가 발생한다.")
    @Test
    void parseToInt_정수아님_예외() {
        // 문자
        assertThatThrownBy(() -> parser.parseToInt("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 정수 이외의 값은 입력할 수 없습니다.");

        // 실수
        assertThatThrownBy(() -> parser.parseToInt("1.5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 정수 이외의 값은 입력할 수 없습니다.");

        // 보너스 번호에 (1개 x) 입력 케이스 ("1,2")
        assertThatThrownBy(() -> parser.parseToInt("1,2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 정수 이외의 값은 입력할 수 없습니다.");
    }

    // --- parseToIntList (당첨 번호용) 테스트 ---

    @DisplayName("[parseToIntList] 빈 값을 입력하면 [ERROR] 빈 값은 입력할 수 없습니다. 예외가 발생한다.")
    @Test
    void parseToIntList_공백_예외() {
        assertThatThrownBy(() -> parser.parseToIntList(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 빈 값은 입력할 수 없습니다.");
    }

    @DisplayName("[parseToIntList] 정수가 아닌 값이 포함되면 예외가 발생한다.")
    @Test
    void parseToIntList_정수아님_예외() {
        // 사용자가 제공한 parseToIntList 코드는 NumberFormatException을 직접 던집니다.
        assertThatThrownBy(() -> parser.parseToIntList("1,2,abc"))
                .isInstanceOf(NumberFormatException.class);
    }
}