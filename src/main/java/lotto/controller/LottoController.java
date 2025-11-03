package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Rank;
import lotto.view.*;
import lotto.domain.LottoMachine;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private Parser parser;
    private LottoMachine lottoMachine;

    public LottoController(InputView inputView, OutputView outputView, Parser parser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.parser = parser;
    }

    public void run() {
        lottoMachine = purchaseLottos();

        outputView.outputPurchasedLottoList(lottoMachine.checkLottos());

        pickWinningLotto();

        showFinalResults();
    }

    private LottoMachine purchaseLottos() {
        while (true) {
            try {
                String rawMoney = inputView.inputMoney();
                int parsedMoney = parser.parseToInt(rawMoney);
                return new LottoMachine(parsedMoney);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void pickWinningLotto() {
        while (true) {
            try {
                String rawWinningLotto = inputView.inputWinningLottoNumbers();
                List<Integer> winningNumbers = parser.parseToIntList(rawWinningLotto);

                String rawBonusNumber = inputView.inputBonusLottoNumber();
                int bonusNumber = parser.parseToInt(rawBonusNumber);

                lottoMachine.pickWinningLotto(winningNumbers, bonusNumber);

                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void showFinalResults() {
        lottoMachine.checkResults();

        Map<Rank, Integer> statistics = lottoMachine.checkResults();
        double rateOfReturn = lottoMachine.getRateOfReturn();

        outputView.printWinningStatistics(statistics, rateOfReturn);
    }
}
