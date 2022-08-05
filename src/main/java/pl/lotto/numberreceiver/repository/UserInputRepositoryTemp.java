package pl.lotto.numberreceiver.repository;

import java.time.Clock;
import java.util.List;

//class just for compiling tests until UserInputRepository moved to other part of application
//class to be deleted in the future
public class UserInputRepositoryTemp implements UserInputRepository{
    @Override
    public UserInput save(UserInput userInput) {
        return null;
    }

    @Override
    public List<UserInput> findAllByDate(Clock date) {
        return null;
    }
}
