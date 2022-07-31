package pl.lotto.numberreceiver.uuidgenerator;

import java.util.Optional;
import java.util.UUID;

public interface UuidGenerable {

    Optional<UUID> generateRandom(String validCondition);
}
