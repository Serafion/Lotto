package pl.lotto.winningnumbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.generator.NumberGenerator;
import pl.lotto.winningnumbergenerator.generator.Randomable;
import pl.lotto.winningnumbergenerator.repository.NumbersService;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersService;

import java.time.Clock;

@Configuration
public class WiningNumberGeneratorConfiguration {
    // to be changed in production due to lack of WinningNumbersRepostory

//    public WiningNumbersGeneratorFacade buildDefaultModuleForProduction(){
//        return buildDefaultModule(Clock.systemDefaultZone(),new WinningNumbersRepositoryTemp(), new NumberReceiverConfiguration().buildDefaultModuleForProduction(), new NumberGenerator());
//    }

    @Bean
    public WiningNumbersGeneratorFacade buildDefaultModule(Clock clock, WinningNumbersRepository repository, NumberReceiverFacade numberReceiverFacade, Randomable generator) {
        WinningNumbersService service = new NumbersService(repository, clock);
        return new WiningNumbersGeneratorFacade(service, generator, numberReceiverFacade);
    }

    public WiningNumbersGeneratorFacade buildModuleForTests(Clock clock, WinningNumbersRepository repository, NumberReceiverFacade numberReceiverFacade) {
        Randomable generator = new NumberGenerator();
        return buildDefaultModule(clock, repository, numberReceiverFacade, generator);
    }
}
