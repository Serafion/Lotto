package pl.lotto.numberreceiver.uuidgenerator;

import java.util.Optional;
import java.util.UUID;

public class UuidGenerator {


    private UuidGeneratorModel model;

    public UuidGenerator() {
        this.model = new UuidGeneratorModel();
    }

    public UuidGenerator(UuidGeneratorModel model) {
        this.model = model;
    }

    public Optional<UUID> generateOptionalUuid(String message) {
        return model.retriveOptional(message);
    }
}
