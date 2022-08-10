package pl.lotto.winningnumbergenerator.repository;

import java.time.LocalDateTime;
import java.util.List;

public interface WinningNumbersRepository {
    List<Integer> retrieveArchivalDraw (LocalDateTime dateTime);
    List<Integer> saveWinningNumbers(LocalDateTime dateTime, List<Integer> numbers);
    boolean containsWinningNumbers(LocalDateTime dateTime);
}
