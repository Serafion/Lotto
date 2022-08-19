package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.winningnumbergenerator.generator.Randomable;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class WiningNumbersGeneratorFacade {

    WinningNumbersService winningNumbersService;
    Randomable generator;
    NumberReceiverFacade numberReceiverFacade;

    public WiningNumbersGeneratorFacade(WinningNumbersService numbersService, Randomable generator, NumberReceiverFacade numberReceiverFacade) {
        this.winningNumbersService = numbersService;
        this.numberReceiverFacade = numberReceiverFacade;
        this.generator = generator;
    }

    public NumberReceiverResultDto retrieveWonNumbersForDate(LocalDateTime dateTime) {
        winningNumbersService.saveNextDrawDate(numberReceiverFacade.outputDrawTime());
        if (!winningNumbersService.containsWinningNumbers(dateTime) && winningNumbersService.isValidDrawDate(dateTime)) {
            NumberReceiverResultDto resultDto = generator.generateNumbers(dateTime);
            winningNumbersService.saveWinningNumbers(dateTime, resultDto.userNumbers());
            return winningNumbersService.provideWinningNumbers(dateTime);
        }
        if (winningNumbersService.containsWinningNumbers(dateTime)) {
            return winningNumbersService.provideWinningNumbers(dateTime);
        }
        return fetchErrorDto(dateTime);
    }

    private NumberReceiverResultDto fetchErrorDto(LocalDateTime dateTime) {
        return new NumberReceiverResultDto("Invalid input provided, numbers not generated", Optional.empty(), List.of(), Optional.of(dateTime));
    }

}
