package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.repository.InputRepository;
import pl.lotto.resultchecker.repository.InputService;
import pl.lotto.resultchecker.repository.NumbersService;
import pl.lotto.resultchecker.resultcalculator.Calculatable;
import pl.lotto.resultchecker.resultcalculator.ResultCalculator;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import java.time.Clock;

public class ResultCheckerConfiguration {
//    public ResultCheckerFacade buildModuleForProduction(){
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildDefaultModuleForProduction();
//        WiningNumbersGeneratorFacade winingNumbersGeneratorFacade = new WiningNumberGeneratorConfiguration().buildDefaultModuleForProduction();
//
//        return buildModuleForProduction(numberReceiverFacade,winingNumbersGeneratorFacade);
//    }

    public ResultCheckerFacade buildDefaultModuleForProduction(NumberReceiverFacade numberReceiverFacade, WiningNumbersGeneratorFacade winingNumbersGeneratorFacade, InputRepository repository, Clock clock) {
        InputService numbersService = new NumbersService(repository, clock);
        Calculatable calculator = new ResultCalculator();
        return new ResultCheckerFacade(numberReceiverFacade, winingNumbersGeneratorFacade, clock, numbersService, calculator);
    }

    public ResultCheckerFacade buildModuleForTest(NumberReceiverFacade numberReceiverFacade, WiningNumbersGeneratorFacade numbersGeneratorFacade, Clock clock, InputRepository inputRepository) {
        return buildDefaultModuleForProduction(numberReceiverFacade, numbersGeneratorFacade, inputRepository, clock);
    }
}
