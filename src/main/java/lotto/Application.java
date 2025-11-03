package lotto;

import lotto.domain.*;
import lotto.view.*;
import lotto.controller.*;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Parser parser = new Parser();
        LottoController lottoController = new LottoController(inputView, outputView, parser);
        lottoController.run();
    }
}
