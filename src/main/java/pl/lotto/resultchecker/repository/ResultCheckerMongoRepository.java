package pl.lotto.resultchecker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;


public interface ResultCheckerMongoRepository extends MongoRepository<CheckerDto, LocalDateTime> {
}
