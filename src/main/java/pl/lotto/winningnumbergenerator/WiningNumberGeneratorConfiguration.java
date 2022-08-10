package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.generator.NumberGenerator;
import pl.lotto.winningnumbergenerator.generator.Randomable;
import pl.lotto.winningnumbergenerator.repository.NumbersService;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepository;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersRepositoryTemp;
import pl.lotto.winningnumbergenerator.repository.WinningNumbersService;
import pl.lotto.winningnumbergenerator.timer.DrawTimer;
import pl.lotto.winningnumbergenerator.timer.TimeSettable;

import java.time.Clock;

public class WiningNumberGeneratorConfiguration {
    // to be changed in production due to lack of WinningNumbersRepostory

    public WiningNumbersGeneratorFacade buildDefaultModuleForProduction(){
        return buildDefaultModule(Clock.systemDefaultZone(),new WinningNumbersRepositoryTemp());
    }

    public WiningNumbersGeneratorFacade buildDefaultModule(Clock clock, WinningNumbersRepository repository){
        WinningNumbersService service = new NumbersService(repository);
        TimeSettable timer = new DrawTimer(clock);
        Randomable generator = new NumberGenerator(service);
        return new WiningNumbersGeneratorFacade(service,timer,generator);
    }

    public WiningNumbersGeneratorFacade buildModuleForTests(Clock clock, WinningNumbersRepository repository){
        return buildDefaultModule(clock,repository);
    }
}
