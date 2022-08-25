package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.repository.WinnerRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class WinnerRepositoryTest implements WinnerRepository {
    @Override
    public Integer getDataFrom(UUID uuid) {
        return null;
    }

    @Override
    public boolean containsWinningNumbers(UUID uuid) {
        return false;
    }

    @Override
    public void saveNewDataToRepository(Map<UUID, Integer> wonNumbersCountMap, LocalDateTime dateTime) {

    }
}
