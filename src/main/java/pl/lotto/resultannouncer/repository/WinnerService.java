package pl.lotto.resultannouncer.repository;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WinnerService {
    private final WinnerRepository winnerRepository;
    private final Map<UUID, WonNumbersCount> wonNumbersCountMap = new HashMap<>();

    public WinnerService(WinnerRepository winnerRepository) {
        this.winnerRepository = winnerRepository;
        wonNumbersCountMap.putAll(winnerRepository.getAllDataFromRepository());
    }

    public void saveNewData(Map<UUID, WonNumbersCount> wonNumbersCountMap) {
        this.wonNumbersCountMap.putAll(wonNumbersCountMap);
        winnerRepository.saveNewDataToRepository(wonNumbersCountMap);
    }

    public WonNumbersCount checkTicketForWinningNumbers(UUID uuid) {
        if (wonNumbersCountMap.containsKey(uuid)) {
            return wonNumbersCountMap.get(uuid);
        }
        return WonNumbersCount.ZERO_NUMBERS_HIT;
    }
}
