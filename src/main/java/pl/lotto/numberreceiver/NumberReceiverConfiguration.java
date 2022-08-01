package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.datastorage.UserInputService;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerator;
import pl.lotto.numberreceiver.validator.Validator;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade buildDefaultModuleForProduction() {
        return buildModuleForProduction(new UuidGenerator(), new UserInputService());
    }

    public NumberReceiverFacade buildModuleForProduction(UuidGenerable generator, UserInputService storage) {
        Validator validator = new Validator();
        return new NumberReceiverFacade(validator, generator, storage);
    }

    public NumberReceiverFacade buildModuleForTests(UuidGenerable generator, UserInputService storage) {
        return buildModuleForProduction(generator, storage);
    }
}
