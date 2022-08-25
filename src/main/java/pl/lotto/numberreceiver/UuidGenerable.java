package pl.lotto.numberreceiver;

import java.util.Optional;
import java.util.UUID;

public interface UuidGenerable {

    Optional<UUID> generateRandom();
}
