package pl.lotto.winningnumbergenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;


@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbersDto, LocalDateTime> {
//    WinningNumbersDto retrieveArchivalDraw(LocalDateTime dateTime);
//
//    WinningNumbersDto saveWinningNumbers(WinningNumbersDto winningNumbersDto);
//
//    boolean containsWinningNumbers(LocalDateTime dateTime);

}
