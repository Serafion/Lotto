package pl.lotto.numberreceiver.repository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserInputRepository {

    UserInput save(UserInput userInput);

    List<UserInput> findAllByDate(LocalDateTime date);

}
