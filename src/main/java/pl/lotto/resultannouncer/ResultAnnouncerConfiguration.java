package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.ResultCheckerFacade;

public class ResultAnnouncerConfiguration {

    public ResultAnnouncerFacade buildModuleForProduction(ResultCheckerFacade resultCheckerFacade, WinnerRepository winnerRepository) {
        return new ResultAnnouncerFacade(resultCheckerFacade, winnerRepository);
    }
}
