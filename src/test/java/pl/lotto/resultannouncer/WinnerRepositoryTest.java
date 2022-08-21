package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WinnerRepositoryTest implements WinnerRepository {
    @Override
    public Map<UUID, WonNumbersCount> getAllDataFromRepository() {
        return new HashMap<>();
    }

    @Override
    public void saveNewDataToRepository(Map<UUID, WonNumbersCount> wonNumbersCountMap) {

    }
}
