package pl.lotto.numberreceiver.uuidgenerator;

import pl.lotto.numberreceiver.configuration.Configuration;

import java.util.Optional;
import java.util.UUID;

class UuidGeneratorModel {

    private Optional<UUID> uuid;

    public Optional<UUID> retriveOptional(String message) {
        if (message.equals(Configuration.CORRECT_MESSAGE)) {
            setUuidToRandom();
        } else {
            this.uuid = Optional.empty();
        }
        return uuid;
    }

    private void setUuidToRandom() {
        this.uuid = Optional.of(UUID.randomUUID());
    }
}
