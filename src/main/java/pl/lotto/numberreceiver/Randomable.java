package pl.lotto.numberreceiver;

import java.util.Optional;
import java.util.UUID;

public interface Randomable {
    Optional<UUID> generateOptionalUuid(String message);
}
