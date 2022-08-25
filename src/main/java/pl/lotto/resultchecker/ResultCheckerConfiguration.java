package pl.lotto.resultchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.repository.InputRepository;
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


    public ResultCheckerFacade buildDefaultModuleForProduction(@Autowired NumberReceiverFacade numberReceiverFacade,
                                                               @Autowired WiningNumbersGeneratorFacade winingNumbersGeneratorFacade,
                                                               InputRepository repository, Clock clock, @Autowired ResultCalculator calculator) {
        return new ResultCheckerFacade(numberReceiverFacade, winingNumbersGeneratorFacade, clock, repository, calculator);
    }

    public ResultCheckerFacade buildModuleForTest(NumberReceiverFacade numberReceiverFacade, WiningNumbersGeneratorFacade numbersGeneratorFacade, Clock clock, InputRepository inputRepository) {
        ResultCalculator calculator = new ResultCalculator();
        return buildDefaultModuleForProduction(numberReceiverFacade, numbersGeneratorFacade, inputRepository, clock, calculator);
    }
}
