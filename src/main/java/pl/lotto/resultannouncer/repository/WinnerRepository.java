package pl.lotto.resultannouncer.repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public interface WinnerRepository {
    Integer getDataFrom(UUID uuid);

    boolean containsWinningNumbers(UUID uuid);

    void saveNewDataToRepository(Map<UUID, Integer> wonNumbersCountMap, LocalDateTime dateTime);
}
