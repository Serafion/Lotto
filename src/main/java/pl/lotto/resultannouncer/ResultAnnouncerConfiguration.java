package pl.lotto.resultannouncer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.ResultCheckerFacade;

@Configuration
public class ResultAnnouncerConfiguration {

    @Bean
    public ResultAnnouncerFacade resultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade, WinnerRepository winnerRepository) {
        return buildModuleForProduction(resultCheckerFacade, winnerRepository);
    }

    public ResultAnnouncerFacade buildModuleForProduction(ResultCheckerFacade resultCheckerFacade, WinnerRepository winnerRepository) {
        return new ResultAnnouncerFacade(resultCheckerFacade, winnerRepository);
    }
}
