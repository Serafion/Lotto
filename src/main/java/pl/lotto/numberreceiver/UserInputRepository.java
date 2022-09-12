package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInputRepository extends MongoRepository<UserInput, UUID> {


//    UserInput save(UserInput userInput);


    List<UserInput> findAllByDate(LocalDateTime date);


//    UserInput findByUniqueLotteryID(UUID uuid);

    UserInput findFirstByDate(LocalDateTime date);
}
