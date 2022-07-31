package pl.lotto.numberreceiver.uuidgenerator;

import java.util.Optional;
import java.util.UUID;

import static pl.lotto.numberreceiver.constants.Constants.CORRECT_MESSAGE;

public class UuidGenerator implements UuidGenerable {


    public UuidGenerator() {
    }


    @Override
    public Optional<UUID> generateRandom(String message) {
        if (message.equals(CORRECT_MESSAGE)) {
            return Optional.of(UUID.randomUUID());
        }
        return Optional.empty();
    }
}
