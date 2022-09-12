package pl.lotto.winningnumbergenerator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;

@Component
public class NumbersRepository implements WinningNumbersRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public WinningNumbersDto retrieveArchivalDraw(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public WinningNumbersDto saveWinningNumbers(WinningNumbersDto winningNumbersDto) {
        return null;
    }

    @Override
    public boolean containsWinningNumbers(LocalDateTime dateTime) {
        return false;
    }
}
