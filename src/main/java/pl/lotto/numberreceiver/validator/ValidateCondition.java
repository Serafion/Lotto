package pl.lotto.numberreceiver.validator;

import pl.lotto.numberreceiver.configuration.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum ValidateCondition {
    CORRECT_INPUT {
        @Override
        String retrieveMessage() {
            return Configuration.CORRECT_MESSAGE;
        }
    },
    LIST_CONTAINS_DUPLICATES {
        @Override
        String retrieveMessage() {
            return Configuration.FAILED_CONTAINS_A_DUPLICATE_NUMBER;
        }

        @Override
        ValidateCondition validateCondition(List<Integer> list) {
            if (list != null) {
                int listLength = list.size();
                int listLengthWhenTurnedIntoSet = Arrays.stream(list.toArray()).collect(Collectors.toSet()).size();
                return listLength == listLengthWhenTurnedIntoSet ? ValidateCondition.CORRECT_INPUT : ValidateCondition.LIST_CONTAINS_DUPLICATES;
            }
            return ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
        }
    },
    LIST_CONTAINS_NUMBER_OUT_OF_RANGE {
        @Override
        String retrieveMessage() {
            return Configuration.FAILED_CONTAINING_NUMBER_NOT_IN_RANGE;
        }

        @Override
        ValidateCondition validateCondition(List<Integer> list) {
            if (list != null) {
                int listLength = list.size();
                int listLengthOfValidNumbers = 0;
                for (Integer i : list) {
                    if (isInRange(i)) {
                        listLengthOfValidNumbers++;
                    if(listLengthOfValidNumbers>Configuration.numbersToDraw){
                        return ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
                    }
                    }
                }
                return listLength == listLengthOfValidNumbers ? ValidateCondition.CORRECT_INPUT : ValidateCondition.LIST_CONTAINS_NUMBER_OUT_OF_RANGE;
            }
            return ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
        }
    },
    LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL {
        @Override
        String retrieveMessage() {
            return Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS;
        }

        @Override
        ValidateCondition validateCondition(List<Integer> list) {
            if (list != null) {
                return list.size() == Configuration.numbersToDraw ? ValidateCondition.CORRECT_INPUT : ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
            }
            return ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
        }
    };
    String retrieveMessage() {
        return "No such Enum";
    }

    ValidateCondition validateCondition(List<Integer> list) {
        return ValidateCondition.CORRECT_INPUT;
    }

    boolean isInRange(Integer i) {
        return i >= Configuration.lowNumberBoundry && i <= Configuration.highNumberBoundry;
    }

}
