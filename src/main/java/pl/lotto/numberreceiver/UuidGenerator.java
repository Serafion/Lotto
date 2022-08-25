package pl.lotto.numberreceiver;

import java.util.Optional;
import java.util.UUID;

class UuidGenerator implements UuidGenerable {

    public UuidGenerator() {
    }

    @Override
    public Optional<UUID> generateRandom() {
        return Optional.of(UUID.randomUUID());
    }
}
