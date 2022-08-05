package pl.lotto.numberreceiver.repository;

import java.time.Clock;
import java.util.List;

public interface UserInputRepository {

    UserInput save(UserInput userInput);

    List<UserInput> findAllByDate(Clock date);

}
