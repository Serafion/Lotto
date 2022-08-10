package pl.lotto.numberreceiver.validator;

import java.util.List;
import static java.util.Arrays.asList;

public class Validator implements Validable {

    List<Conditionable> conditions = asList(
            new DuplicateChecker(),
            new NumberQuantityChecker(),
            new NumbersRangeChecker()
    );

    @Override
    public ValidateMessage retrieveMessageForGivenInput(List<Integer> numbersFromUser) {
        for (Conditionable condition : conditions) {
            ValidateMessage conditionMessage = condition.validateCondition(numbersFromUser);
            if (!conditionMessage.equals(ValidateMessage.CORRECT_MESSAGE)) {
                return conditionMessage;
            }
        }
        return ValidateMessage.CORRECT_MESSAGE;
    }
}
