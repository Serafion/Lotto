package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dategenerator.Clockable;
import pl.lotto.numberreceiver.dategenerator.DateGenerator;
import pl.lotto.numberreceiver.repository.UserInputRepository;
import pl.lotto.numberreceiver.repository.UserInputRepositoryTemp;
import pl.lotto.numberreceiver.repository.UserInputService;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerable;
import pl.lotto.numberreceiver.uuidgenerator.UuidGenerator;
import pl.lotto.numberreceiver.validator.Validable;
import pl.lotto.numberreceiver.validator.Validator;

import java.time.Clock;

public class NumberReceiverConfiguration {


    public NumberReceiverFacade buildDefaultModuleForProduction() {
        Clock clock = Clock.systemDefaultZone();
        return buildModuleForProduction(new UuidGenerator(), new UserInputRepositoryTemp(), clock);
    }

    public NumberReceiverFacade buildModuleForProduction(UuidGenerable generator, UserInputRepository storage, Clock clock) {
        Validable validator = new Validator();
        Clockable dataGenerator = new DateGenerator(clock);
        UserInputService repository = new UserInputService(storage);
        return new NumberReceiverFacade(validator, generator, dataGenerator, repository);
    }

    public NumberReceiverFacade buildModuleForTests(UuidGenerable generator, UserInputRepository storage, Clock clock) {
        return buildModuleForProduction(generator, storage, clock);
    }
}
