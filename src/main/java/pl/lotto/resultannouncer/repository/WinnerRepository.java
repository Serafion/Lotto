package pl.lotto.resultannouncer.repository;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.util.Map;
import java.util.UUID;

public interface WinnerRepository {
    Map<UUID, WonNumbersCount> getAllDataFromRepository();

    void saveNewDataToRepository(Map<UUID, WonNumbersCount> wonNumbersCountMap);
}
