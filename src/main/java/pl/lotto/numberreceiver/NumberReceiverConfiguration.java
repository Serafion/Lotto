package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerator;
import pl.lotto.numberreceiver.validator.ValidatorModel;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade buildDefaultModuleForProduction() {
        return buildModuleForProduction(new UuidGenerator());
    }

    public NumberReceiverFacade buildModuleForProduction(UuidGenerable generator) {
        ValidatorModel validator = new ValidatorModel();
        return new NumberReceiverFacade(validator, generator);
    }

    public NumberReceiverFacade buildModuleForTests(UuidGenerable generator) {
        return buildModuleForProduction(generator);
    }
}
