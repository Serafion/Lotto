package pl.lotto.numberreceiver;

public class UserInputNotFoundException extends RuntimeException {

    public UserInputNotFoundException(String message) {
        super(message);
    }
}
