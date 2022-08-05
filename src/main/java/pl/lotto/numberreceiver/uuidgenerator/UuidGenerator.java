package pl.lotto.numberreceiver.uuidgenerator;

import java.util.Optional;
import java.util.UUID;

public class UuidGenerator implements UuidGenerable {

    public UuidGenerator() {
    }

    @Override
    public Optional<UUID> generateRandom() {
        return Optional.of(UUID.randomUUID());
    }
}
