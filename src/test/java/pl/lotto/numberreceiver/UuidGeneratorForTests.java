package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;

import java.util.Optional;
import java.util.UUID;

public class UuidGeneratorForTests implements UuidGenerable {
    @Override
    public Optional<UUID> generateRandom() {
        return Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002"));
    }
}
