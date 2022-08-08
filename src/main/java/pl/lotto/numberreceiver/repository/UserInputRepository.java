package pl.lotto.numberreceiver.repository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserInputRepository {

    UserInput save(UserInput userInput);

    List<UserInput> findAllByDate(LocalDateTime date);

}
