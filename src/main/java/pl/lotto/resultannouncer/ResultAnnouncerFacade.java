package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.messenger.Messenger;
import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultannouncer.repository.WinnerService;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.util.Map;
import java.util.UUID;

public class ResultAnnouncerFacade {
    private final ResultCheckerFacade resultChecker;
    private final WinnerRepository winnerRepository;
    private final WinnerService winnerService;

    public ResultAnnouncerFacade(ResultCheckerFacade resultChecker, WinnerRepository winnerRepository, WinnerService winnerService) {
        this.resultChecker = resultChecker;
        this.winnerRepository = winnerRepository;
        this.winnerService = winnerService;
    }

    public String checkWinner(UUID uuid) {
        Map<UUID, WonNumbersCount> map = resultChecker.checkWinners(uuid);
        winnerService.saveNewData(map);
        WonNumbersCount wonNumbersCount = winnerService.checkTicketForWinningNumbers(uuid);
        return Messenger.fetchMessage(wonNumbersCount);
    }
}
