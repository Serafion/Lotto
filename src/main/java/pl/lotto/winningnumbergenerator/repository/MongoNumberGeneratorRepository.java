package pl.lotto.winningnumbergenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lotto.winningnumbergenerator.winningnumbersdto.WinningNumbersDto;

import java.time.LocalDateTime;

public interface MongoNumberGeneratorRepository extends MongoRepository<WinningNumbersDto, LocalDateTime> {
}
