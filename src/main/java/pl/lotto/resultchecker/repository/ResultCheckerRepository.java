package pl.lotto.resultchecker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lotto.resultchecker.checkerdto.CheckerDto;

import java.time.LocalDateTime;


@Repository
public interface ResultCheckerRepository extends MongoRepository<CheckerDto, LocalDateTime> {

}
