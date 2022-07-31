package pl.lotto.numberreceiver.validator;

import java.util.ArrayList;
import java.util.List;

import static pl.lotto.numberreceiver.validator.ValidateCondition.CORRECT_INPUT;

public class Validator {


    public String retriveMessageForGivenInput(List<Integer> list) {
        ValidateCondition condition = returnConditionForInput(list);
        return condition.retrieveMessage();
    }

    private ValidateCondition returnConditionForInput(List<Integer> list) {
        List<ValidateCondition> validateConditions = conditionChecker(list);
        for (ValidateCondition i : validateConditions) {
            if (!i.equals(CORRECT_INPUT)) {
                return i;
            }
        }
        return CORRECT_INPUT;
    }

    private List<ValidateCondition> conditionChecker(List<Integer> list) {
        List<ValidateCondition> checkedConditions = new ArrayList<>();
        for (ValidateCondition conditionToCheck : ValidateCondition.values()) {
            checkedConditions.add(conditionToCheck.validateCondition(list));
        }
        return checkedConditions;
    }
}
