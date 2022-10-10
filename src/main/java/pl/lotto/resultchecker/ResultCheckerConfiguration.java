package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberprovider.NumberProviderFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

@Configuration
public class ResultCheckerConfiguration {
//    public ResultCheckerFacade buildModuleForProduction(){
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildDefaultModuleForProduction();
//        WiningNumbersGeneratorFacade winingNumbersGeneratorFacade = new WiningNumberGeneratorConfiguration().buildDefaultModuleForProduction();
//
//        return buildModuleForProduction(numberReceiverFacade,winingNumbersGeneratorFacade);
//    }


    @Bean
    public ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                                                   ResultCheckerRepository resultCheckerRepository,
                                                   ResultCalculator calculator,
                                                   NumberProviderFacade numberProviderFacade) {
        return new ResultCheckerFacade(numberReceiverFacade, resultCheckerRepository, calculator, numberProviderFacade);
    }

    public ResultCheckerFacade buildModuleForTest(NumberReceiverFacade numberReceiverFacade, ResultCheckerRepository inputRepository, NumberProviderFacade numberProviderFacade) {
        ResultCalculator calculator = new ResultCalculator();
        return resultCheckerFacade(numberReceiverFacade, inputRepository, calculator, numberProviderFacade);
    }
}
