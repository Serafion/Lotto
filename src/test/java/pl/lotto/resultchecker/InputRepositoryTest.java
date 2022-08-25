package pl.lotto.resultchecker;

import pl.lotto.resultchecker.checkerdto.CheckerDto;
import pl.lotto.resultchecker.repository.InputRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class InputRepositoryTest implements InputRepository {
    @Override
    public Set<LocalDateTime> retrieveDrawDates() {
        return new HashSet<>();
    }

    @Override
    public CheckerDto retrieveData(UUID uuid) {
        return null;
    }

    @Override
    public LocalDateTime drawDate(UUID uuid) {
        return null;
    }

    @Override
    public boolean containsWinningNumberForDrawDate(LocalDateTime drawDate) {
        return false;
    }

    @Override
    public void updateData(Map<UUID, Integer> data, LocalDateTime dateTime) {

    }

    @Override
    public boolean dataContainsTicket(UUID uuid) {
        return false;
    }

    @Override
    public void updateDrawDates(LocalDateTime drawDate) {

    }
}
