package pl.lotto.resultchecker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Component
public class RepositoryImpl implements InputRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Set<LocalDateTime> retrieveDrawDates() {
        return null;
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
    public void updateDrawDates(LocalDateTime drawDate) {

    }

    @Override
    public void updateData(Map<UUID, Integer> data, LocalDateTime dateTime) {

    }

    @Override
    public boolean dataContainsTicket(UUID uuid) {
        return false;
    }
}
