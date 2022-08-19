package pl.lotto.winningnumbergenerator.repository;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class NumbersService implements WinningNumbersService{

    private final WinningNumbersRepository repository;
    private final DrawTimer timer;

    public NumbersService(WinningNumbersRepository repository, Clock clock) {
        this.repository = repository;
        this.timer = new DrawTimer(clock);
        timer.updateDrawDates(repository.returnDrawDates());
    }

    @Override
    public NumberReceiverResultDto provideWinningNumbers(LocalDateTime dateTime) {
        return repository.retrieveArchivalDraw(dateTime);
    }

    @Override
    public boolean containsWinningNumbers(LocalDateTime dateTime) {
        return repository.containsWinningNumbers(dateTime);
    }

    @Override
    public boolean isValidDrawDate(LocalDateTime dateTime) {
        return timer.ItsTimeToMakeADraw(dateTime);
    }

    @Override
    public NumberReceiverResultDto saveWinningNumbers(LocalDateTime dateTime, List<Integer> winningNumbers) {
        repository.saveWinningNumbers(dateTime, winningNumbers);
        return repository.retrieveArchivalDraw(dateTime);
    }

    @Override
    public void saveNextDrawDate(LocalDateTime dateTime) {
        timer.addDrawDate(dateTime);
    }
}
