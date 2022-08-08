package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;
import java.util.List;
import pl.lotto.winningnumbergenerator.datevalidator.Validator;
import pl.lotto.winningnumbergenerator.generator.NumberGenerator;

public class WiningNumbersGeneratorFacade implements NumberProvidable {

    public WiningNumbersGeneratorFacade() {
    }

    @Override
    public List<Integer> retrieveWonNumbersForDate(LocalDateTime dateTime) {
        Validator validator = new Validator();
        NumberGenerator generator = new NumberGenerator();
        boolean validate = validator.validateDateOfDraw(dateTime);

        return generator.generateNumbers(validate);
    }
}
