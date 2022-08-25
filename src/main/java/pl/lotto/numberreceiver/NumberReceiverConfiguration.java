package pl.lotto.numberreceiver;

import java.time.Clock;

public class NumberReceiverConfiguration {


//    public NumberReceiverFacade buildDefaultModuleForProduction() {
//        Clock clock = Clock.systemDefaultZone();
//        return buildModuleForProduction(new UuidGenerator(), new UserInputRepositoryTemp(), clock);
//    }

    public NumberReceiverFacade buildModuleForProduction(UuidGenerable generator, UserInputRepository storage, Clock clock) {
        Validator validator = new Validator();
        DateGenerator dataGenerator = new DateGenerator(clock);
        UserInputService repository = new UserInputService(storage);
        return new NumberReceiverFacade(validator, generator, dataGenerator, repository);
    }

    public NumberReceiverFacade buildModuleForTests(UuidGenerable generator, UserInputRepository storage, Clock clock) {
        return buildModuleForProduction(generator, storage, clock);
    }
}
