package pl.lotto.numberreceiver.validator;

import pl.lotto.numberreceiver.configuration.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class ValidatorModel {

    private List<Integer> numbersFromUser;


    public String retriveMessageForGivenInput(List<Integer> list) {
        this.numbersFromUser = list;
        ValidateCondition condition = returnConditionForInput();
        switch (condition) {
            case CORRECT_INPUT -> {
                return Configuration.CORRECT_MESSAGE;
            }
            case LIST_CONTAINS_DUPLICATES -> {
                return Configuration.FAILED_CONTAINS_A_DUPLICATE_NUMBER;
            }
            case LIST_CONTAINS_NUMBER_OUT_OF_RANGE -> {
                return Configuration.FAILED_CONTAINING_NUMBER_NOT_IN_RANGE;
            }
            case LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL -> {
                return Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS;
            }
        }
        return "error";
    }

    private ValidateCondition returnConditionForInput() {

        List<ValidateCondition> validateConditions = conditionChecker();
        for (ValidateCondition i : validateConditions) {
            if (!i.equals(ValidateCondition.CORRECT_INPUT)) {
                return i;
            }
        }
        return ValidateCondition.CORRECT_INPUT;
    }

    private List<ValidateCondition> conditionChecker() {
        if (isNull()) {
            return Collections.singletonList(ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL);
        }
        List<ValidateCondition> listOfCondtion = new ArrayList<>() {
        };
        listOfCondtion.add(doesNotContainDuplicates());
        listOfCondtion.add(doesContainOnlyNumbersInRange());
        listOfCondtion.add(doesContainExactllySixNumbers());
        return listOfCondtion;
    }

    private boolean isNull() {
        return numbersFromUser==null;
    }

    private ValidateCondition doesNotContainDuplicates() {
        int listLength = numbersFromUser.size();
        int listLengthWhenTurnedIntoSet = Arrays.stream(numbersFromUser.toArray()).collect(Collectors.toSet()).size();
        return listLength == listLengthWhenTurnedIntoSet ? ValidateCondition.CORRECT_INPUT : ValidateCondition.LIST_CONTAINS_DUPLICATES;

    }

    private ValidateCondition doesContainOnlyNumbersInRange() {
        int listLength = numbersFromUser.size();
        int listLengthOfValidNumbers = 0;
        for (Integer i : numbersFromUser) {
            if (isInRange(i)) {
                listLengthOfValidNumbers++;
            }
        }
        return listLength == listLengthOfValidNumbers ? ValidateCondition.CORRECT_INPUT : ValidateCondition.LIST_CONTAINS_NUMBER_OUT_OF_RANGE;
    }

    private ValidateCondition doesContainExactllySixNumbers() {
        return numbersFromUser.size() == 6 ? ValidateCondition.CORRECT_INPUT : ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
    }

    private boolean isInRange(Integer i) {
        return i >= Configuration.lowNumberBoundry && i <= Configuration.highNumberBoundry;
    }
}
