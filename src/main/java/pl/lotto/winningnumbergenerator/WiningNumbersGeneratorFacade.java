package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.generator.Randomable;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersService;
import pl.lotto.winningnumbergenerator.timer.TimeSettable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class WiningNumbersGeneratorFacade {

    WinningNumbersService winningNumbersService;
    TimeSettable timer;
    Randomable generator;

    public WiningNumbersGeneratorFacade(WinningNumbersService numbersService, TimeSettable timer, Randomable generator) {
        this.winningNumbersService = numbersService;
        this.timer = timer;
        this.generator = generator;
    }

    public List<Integer> retrieveWonNumbersForDate(LocalDateTime dateTime) {
        if(!winningNumbersService.containsWinningNumbers(dateTime)&&timer.ItsTimeToMakeADraw(dateTime)){
            generator.generateNumbersToRepository(dateTime);
            return winningNumbersService.provideWinningNumbers(dateTime);
        }
        if (winningNumbersService.containsWinningNumbers(dateTime)&&timer.currentTime().isAfter(dateTime)) {
            return winningNumbersService.provideWinningNumbers(dateTime);
        }
        return Arrays.asList();
    }

}
