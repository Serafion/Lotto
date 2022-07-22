package pl.lotto.numberreceiver.validator;

import java.util.ArrayList;
import java.util.List;

class ValidatorModel {


    public String retriveMessageForGivenInput(List<Integer> list) {
        ValidateCondition condition = returnConditionForInput(list);
        return condition.retrieveMessage();
    }

    private ValidateCondition returnConditionForInput(List<Integer> list) {

        List<ValidateCondition> validateConditions = conditionChecker(list);
        for (ValidateCondition i : validateConditions) {
            if (!i.equals(ValidateCondition.CORRECT_INPUT)) {
                return i;
            }
        }
        return ValidateCondition.CORRECT_INPUT;
    }

    private List<ValidateCondition> conditionChecker(List<Integer> list) {
        List<ValidateCondition> checkedConditions = new ArrayList<>();
        for (ValidateCondition conditionToCheck : ValidateCondition.values()) {
            checkedConditions.add(conditionToCheck.validateCondition(list));
        }
        return checkedConditions;
    }
}
