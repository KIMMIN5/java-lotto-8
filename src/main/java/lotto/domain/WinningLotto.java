package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
    private final int bonusNumber;

    public WinningLotto(List<Integer> lottoNumbers, int bonusNumber) {
        super(lottoNumbers);
        LottoRules.validateDuplication(lottoNumbers);
        LottoRules.validateNumber(bonusNumber);
        LottoRules.validateDuplicateBonus(lottoNumbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
