package pl.lotto.numberreceiver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserInputRepository extends MongoRepository<UserInput, UUID> {

    List<UserInput> findAllByDate(LocalDateTime date);
}
