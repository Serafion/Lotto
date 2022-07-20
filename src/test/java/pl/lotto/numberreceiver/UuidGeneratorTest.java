package pl.lotto.numberreceiver;

import java.util.Optional;
import java.util.UUID;

public class UuidGeneratorTest implements Randomable{
    Optional<UUID> uuid;

    public UuidGeneratorTest(Optional<UUID> uuid) {
        this.uuid = uuid;
    }

    @Override
    public Optional<UUID> generateOptionalUuid(String message) {
        return uuid;
    }
}
