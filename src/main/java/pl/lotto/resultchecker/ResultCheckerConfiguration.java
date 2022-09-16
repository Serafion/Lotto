package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.repository.DrawDateRepository;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import java.time.Clock;

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
                                                   WiningNumbersGeneratorFacade winingNumbersGeneratorFacade,
                                                   ResultCheckerRepository resultCheckerRepository, Clock clock, ResultCalculator calculator, DrawDateRepository drawDateRepository) {
        return new ResultCheckerFacade(drawDateRepository, numberReceiverFacade, winingNumbersGeneratorFacade, clock, resultCheckerRepository, calculator);
    }

    public ResultCheckerFacade buildModuleForTest(NumberReceiverFacade numberReceiverFacade, WiningNumbersGeneratorFacade numbersGeneratorFacade, Clock clock, ResultCheckerRepository inputRepository, DrawDateRepository drawDateRepository) {
        ResultCalculator calculator = new ResultCalculator();
        return resultCheckerFacade(numberReceiverFacade, numbersGeneratorFacade, inputRepository, clock, calculator, drawDateRepository);
    }
}
