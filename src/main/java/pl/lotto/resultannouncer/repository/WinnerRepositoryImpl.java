package pl.lotto.resultannouncer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

//@Component
//public class WinnerRepositoryImpl implements WinnerRepository {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public Integer getDataFrom(UUID uuid) {
//        return null;
//    }
//
//    @Override
//    public boolean containsWinningNumbers(UUID uuid) {
//        return false;
//    }
//
//    @Override
//    public void saveNewDataToRepository(Map<UUID, Integer> wonNumbersCountMap, LocalDateTime dateTime) {
//
//    }
//}
