package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;
import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class WiningNumbersGeneratorFacade {

    WinningNumbersRepository numRepo;
    DrawTimer timer;
    NumberGenerator generator;
    Clock clock;

    public WiningNumbersGeneratorFacade(WinningNumbersRepository numRepo, DrawTimer timer, NumberGenerator generator) {
        this.numRepo = numRepo;
        this.timer = timer;
        this.generator = generator;
    }

    public WinningNumbersDto retrieveWonNumbersForDate(LocalDateTime dateTime) {
        if (!numRepo.containsWinningNumbers(dateTime) && timer.itsTimeToMakeADraw(dateTime)) {
            WinningNumbersDto result = generator.generateNumbers(dateTime);
            numRepo.saveWinningNumbers(result);
            return result;
        }
        if (numRepo.containsWinningNumbers(dateTime) && timer.currentTime().isAfter(dateTime)) {
            return numRepo.retrieveArchivalDraw(dateTime);
        }
        return WinningNumbersMapper.toDto(List.of(), dateTime);
    }

}
