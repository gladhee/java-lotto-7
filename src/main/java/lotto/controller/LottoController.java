package lotto.controller;

import lotto.domain.Amount;
import lotto.domain.Lottos;
import lotto.service.LottoService;
import lotto.service.WinningService;
import lotto.view.input.InputView;
import lotto.view.output.ErrorView;
import lotto.view.output.LottoView;

public class LottoController {

    private final LottoService lottoService;
    private final WinningService winningService;

    public LottoController(LottoService lottoService, WinningService winningService) {
        this.lottoService = lottoService;
        this.winningService = winningService;
    }

    public void run() {
        Amount amount = getLottoAmount();
        Lottos lottos = lottoService.createLottos(amount);
        LottoView.printLottos(lottos);

        InputView.closeStream();
    }

    private Amount getLottoAmount() {
        while (true) {
            try {
                String purchaseAmount = InputView.inputAmount();

                return Amount.of(purchaseAmount);
            } catch (IllegalArgumentException e) {
                ErrorView.printError(e);
            }
        }
    }

}