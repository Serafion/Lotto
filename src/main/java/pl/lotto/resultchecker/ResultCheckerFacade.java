package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultchecker.repository.InputService;
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

    NumberReceiverFacade numberReceiverFacade;
    WiningNumbersGeneratorFacade numbersGeneratorFacade;
    InputService inputService;
    Calculatable calculator;
    Clock clock;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, WiningNumbersGeneratorFacade numbersGeneratorFacade, Clock clock, InputService inputRepository, Calculatable calculator) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGeneratorFacade = numbersGeneratorFacade;
        this.clock = clock;
        this.inputService = inputRepository;
        this.calculator = calculator;
    }

    Map<UUID, WonNumbersCount> checkWinners(UUID uuid) {
        updateNextDrawDate();
        LocalDateTime drawDate = inputService.getLastPasedDrawDate();
        if (inputService.uuidPresent(uuid)) {
            return inputService.fetchResults(uuid);
        }
        if (inputService.serviceContainsDrawDate(drawDate)) {
            List<NumberReceiverResultDto> inputs = numberReceiverFacade.retrieveNumbersForDate(drawDate);
            NumberReceiverResultDto wonNumbers = numbersGeneratorFacade.retrieveWonNumbersForDate(drawDate);
            Map<UUID, WonNumbersCount> map = calculator.calculateResults(inputs, wonNumbers);
            return map.containsKey(uuid) ? map : new HashMap<>();
        }
        return new HashMap<>();
    }

    private LocalDateTime updateNextDrawDate() {
        LocalDateTime drawDate = numberReceiverFacade.outputDrawTime();
        inputService.addDate(drawDate);
        return drawDate;
    }
}
