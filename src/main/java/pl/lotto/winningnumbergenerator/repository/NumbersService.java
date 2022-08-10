package pl.lotto.winningnumbergenerator.repository;

import java.time.LocalDateTime;
import java.util.List;

public class NumbersService implements WinningNumbersService{

    private final WinningNumbersRepository repository;

    public NumbersService(WinningNumbersRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Integer> provideWinningNumbers(LocalDateTime dateTime) {
        return repository.retrieveArchivalDraw(dateTime);
    }

    @Override
    public boolean containsWinningNumbers(LocalDateTime dateTime) {
        return repository.containsWinningNumbers(dateTime);
    }

    @Override
    public List<Integer> saveWinningNumbers(LocalDateTime dateTime, List<Integer> winningNumbers) {
        repository.saveWinningNumbers(dateTime,winningNumbers);
        return repository.retrieveArchivalDraw(dateTime);
    }
}
