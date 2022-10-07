package pl.lotto.numberreceiver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserInputRepository extends MongoRepository<UserInput, UUID> {


//    UserInput save(UserInput userInput);


    List<UserInput> findAllByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime dateTime);


//    UserInput findByUniqueLotteryID(UUID uuid);

    UserInput findFirstByDate(LocalDateTime date);
}
