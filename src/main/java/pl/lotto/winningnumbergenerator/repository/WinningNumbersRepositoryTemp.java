package pl.lotto.winningnumbergenerator.repository;

import java.time.LocalDateTime;
import java.util.List;

public class WinningNumbersRepositoryTemp implements WinningNumbersRepository{
    @Override
    public List<Integer> retrieveArchivalDraw(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public List<Integer> saveWinningNumbers(LocalDateTime dateTime, List<Integer> numbers) {
        return null;
    }

    @Override
    public boolean containsWinningNumbers(LocalDateTime dateTime) {
        return false;
    }
}
