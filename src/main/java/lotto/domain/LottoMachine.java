package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;

public class LottoMachine {
    private int money;
    private List<Lotto> lottos;
    private WinningLotto winningLotto;
    private double rateOfReturn;
    private LottoResultChecker lottoResultChecker;
    private Map<Rank, Integer> statistics;

    public LottoMachine(int money) {
        LottoRules.validateMoney(money);
        this.money = money;
        this.lottos = generateLottos();
        this.rateOfReturn = 0.0;
        this.lottoResultChecker = new LottoResultChecker();
        this.statistics = new EnumMap<>(Rank.class);
    }

    private List<Integer> generateLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private List<Lotto> generateLottos() {
        int quantity = money / 1000;
        List<Lotto> lottos = new ArrayList<>();
        for(int i=0; i<quantity; i++) {
            lottos.add(new Lotto(generateLottoNumbers()));
        }
        return List.copyOf(lottos);
    }

    public void pickWinningLotto(List<Integer> numbers, int bonusNumber) {
        LottoRules.validateDuplication(numbers);
        LottoRules.validateNumber(bonusNumber);
        this.winningLotto = new WinningLotto(numbers, bonusNumber);
    }

    public List<Lotto> checkLottos() {
        return List.copyOf(lottos);
    }

    public Map<Rank, Integer> checkResults() {
        this.statistics = lottoResultChecker.CheckResults(this.lottos, this.winningLotto);
        calculateRateOfReturn();
        return statistics;
    }

    private void calculateRateOfReturn() {
        long total = 0;
        for(Rank rank: statistics.keySet()) {
            total += (long)rank.getPrize() * statistics.get(rank);
        }

        if(this.money == 0) {
            this.rateOfReturn = 0.0;
            return;
        }

        this.rateOfReturn = (double)total / this.money * 100.0;
    }

    public double getRateOfReturn() {
        return this.rateOfReturn;
    }

}
