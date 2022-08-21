package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultannouncer.repository.WinnerService;
import pl.lotto.resultchecker.ResultCheckerFacade;

public class ResultAnnouncerConfiguration {

    public ResultAnnouncerFacade buildModuleForProduction(ResultCheckerFacade resultCheckerFacade, WinnerRepository winnerRepository) {
        WinnerService winnerService = new WinnerService(winnerRepository);
        return new ResultAnnouncerFacade(resultCheckerFacade, winnerRepository, winnerService);
    }
}
