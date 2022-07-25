package pl.lotto.numberreceiver.uuidgenerator;

import java.util.Optional;
import java.util.UUID;

public class UuidGenerator implements UuidGenerable {

    private final UuidGeneratorModel model;

    public UuidGenerator() {
        this.model = new UuidGeneratorModel();
    }

    //Test purposes
    public UuidGenerator(UuidGeneratorModel model) {
        this.model = model;
    }

    @Override
    public Optional<UUID> generateRandom(String message) {
        return model.retriveOptional(message);
    }
}
