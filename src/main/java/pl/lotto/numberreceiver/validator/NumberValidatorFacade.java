package pl.lotto.numberreceiver.validator;

import java.util.List;


public class NumberValidatorFacade {

    private final ValidatorModel validator = new ValidatorModel();

    public NumberValidatorFacade() {
    }

    public String generateMessageFromReceivedInput(List<Integer> list) {
        return validator.retriveMessageForGivenInput(list);
    }
}
