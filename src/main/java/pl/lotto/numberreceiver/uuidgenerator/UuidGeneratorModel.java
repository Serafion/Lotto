package pl.lotto.numberreceiver.uuidgenerator;

import pl.lotto.numberreceiver.configuration.Configuration;

import java.util.Optional;
import java.util.UUID;

class UuidGeneratorModel {

    public Optional<UUID> retriveOptional(String message) {
        if (message.equals(Configuration.CORRECT_MESSAGE)) {
            return Optional.of(UUID.randomUUID());
        }
        return Optional.empty();
    }

}