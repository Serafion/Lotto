package pl.lotto.resultchecker.repository;

import pl.lotto.resultchecker.resultcalculator.WonNumbersCount;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NumbersService implements InputService {
    private final InputRepository inputRepository;
    private final Map<UUID, WonNumbersCount> map;
    private final Set<LocalDateTime> dates;
    private final Clock clock;

    public NumbersService(InputRepository inputRepository, Clock clock) {
        this.clock = clock;
        this.inputRepository = inputRepository;
        this.map = inputRepository.retrieveAllData();
        this.dates = inputRepository.retrieveDrawDates();
    }

    @Override
    public boolean uuidPresent(UUID uuid) {
        return map.containsKey(uuid);
    }

    @Override
    public Map<UUID, WonNumbersCount> fetchResults(UUID uuid) {
        return inputRepository.retrieveData(uuid);
    }

    @Override
    public boolean serviceContainsDrawDate(LocalDateTime drawDate) {
        return dates.contains(drawDate);
    }

    @Override
    public void addDate(LocalDateTime dateTime) {
        dates.add(dateTime);
    }

    @Override
    public LocalDateTime getLastPasedDrawDate() {
        LocalDateTime currentDate = LocalDateTime.now(clock);
        LocalDateTime drawDate = currentDate.plusDays(-10);
        LocalDateTime temp;
        for (LocalDateTime date : dates) {
            temp = date;
            if (date.isBefore(currentDate) && date.isAfter(drawDate)) {
                drawDate = temp;
            }
        }
        return drawDate;
    }
}
