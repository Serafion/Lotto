package pl.lotto.numberreceiver.validator;

import java.util.ArrayList;
import java.util.List;

public class Validator implements Validable {

    @Override
    public ValidateMessage retrieveMessageForGivenInput(List<Integer> numbersFromUser) {
        List<Conditionable> validateConditions = fetchConditionList(numbersFromUser);
        for (Conditionable i : validateConditions) {
            ValidateMessage conditionMessage = i.validateCondition(numbersFromUser);
            if (!conditionMessage.equals(ValidateMessage.CORRECT_MESSAGE)) {
                return conditionMessage;
            }
        }
        return ValidateMessage.CORRECT_MESSAGE;
    }

    private List<Conditionable> fetchConditionList(List<Integer> list) {
        List<Conditionable> conditions = new ArrayList<>();
        conditions.add(new CheckDuplicates());
        conditions.add(new CheckForSixNumbers());
        conditions.add(new CheckNumbersRange());
        return conditions;
    }
}
