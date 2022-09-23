package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class NumberReceiverConfiguration {


    //Test of making spring boot working
    @Bean
    public NumberReceiverFacade numberReceiverFacade(UserInputRepository userInputRepository, Clock clock) {
//        Clock clock = Clock.systemDefaultZone();
        return buildModuleForProduction(new UuidGenerator(), userInputRepository, clock);
    }


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
