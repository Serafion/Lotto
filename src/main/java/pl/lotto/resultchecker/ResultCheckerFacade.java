package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultchecker.dataretriever.Retrievable;
import pl.lotto.resultchecker.repository.Updatable;
import pl.lotto.resultchecker.resultcalculator.Calculatable;
import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ResultCheckerFacade {

    private final Updatable inputRepository;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WiningNumbersGeneratorFacade numbersGeneratorFacade;
    private final Calculatable calculator;
    private final Clock clock;
    private final Retrievable importer;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, WiningNumbersGeneratorFacade numbersGeneratorFacade, Clock clock, Updatable inputRepository, Calculatable calculator, Retrievable importer) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGeneratorFacade = numbersGeneratorFacade;
        this.importer = importer;
        this.clock = clock;
        this.inputRepository = inputRepository;
        this.calculator = calculator;
    }

    Map<UUID, WonNumbersCount> checkWinners(UUID uuid) {
        if (inputRepository.dataContainsTicket(uuid)) {
            return inputRepository.fetchResults();
        }
        LocalDateTime drawDate = importer.calculateLastDrawDate();
        if (inputRepository.serviceContainsDrawDate(drawDate)) {
            return inputRepository.fetchResults();
        }
        List<NumberReceiverResultDto> inputs = importer.fetchNewTickets();
        List<Integer> wonNumbers = importer.fetchWonNumbers();
        Map<UUID, WonNumbersCount> map = calculator.calculateResults(inputs, wonNumbers);
        return map.containsKey(uuid) ? map : new HashMap<>();
    }

//    Map<UUID, WonNumbersCount> checkWinners(UUID uuid) {
//        numberReceiverFacade.retrieveNumbersForDate()
//    }


}
