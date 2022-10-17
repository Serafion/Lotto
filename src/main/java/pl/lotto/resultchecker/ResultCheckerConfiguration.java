package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.infrastructure.httpclient.resultchecker.NumberProviderClient;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

@Configuration
public class ResultCheckerConfiguration {

    @Bean
    public ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                                                   ResultCheckerRepository resultCheckerRepository,
                                                   ResultCalculator calculator,
                                                   NumberProvider numberProvider) {
        return new ResultCheckerFacade(numberReceiverFacade, resultCheckerRepository, calculator, numberProvider);
    }

    public ResultCheckerFacade buildModuleForTest(NumberReceiverFacade numberReceiverFacade, ResultCheckerRepository inputRepository, NumberProviderClient numberProvider) {
        ResultCalculator calculator = new ResultCalculator();
        return resultCheckerFacade(numberReceiverFacade, inputRepository, calculator, numberProvider);
    }
}
