package pl.lotto.numberreceiver.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static pl.lotto.numberreceiver.configuration.Constants.CORRECT_MESSAGE;
import static pl.lotto.numberreceiver.configuration.Constants.FAILED_CONTAINING_NUMBER_NOT_IN_RANGE;
import static pl.lotto.numberreceiver.configuration.Constants.FAILED_CONTAINS_A_DUPLICATE_NUMBER;
import static pl.lotto.numberreceiver.configuration.Constants.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS;
import static pl.lotto.numberreceiver.configuration.Constants.HIGH_NUMBER_BOUNDRY;
import static pl.lotto.numberreceiver.configuration.Constants.LOW_NUMBER_BOUNDRY;
import static pl.lotto.numberreceiver.configuration.Constants.NUMBERS_TO_DRAW;

enum ValidateCondition {
    CORRECT_INPUT {
        @Override
        String retrieveMessage() {
            return CORRECT_MESSAGE;
        }
    },
    LIST_CONTAINS_DUPLICATES {
        @Override
        String retrieveMessage() {
            return FAILED_CONTAINS_A_DUPLICATE_NUMBER;
        }

        @Override
        ValidateCondition validateCondition(List<Integer> list) {
            if (list != null) {
                int listLength = list.size();
                int numbersCount = countNumbersFromUser(list);
                return listLength == numbersCount ? ValidateCondition.CORRECT_INPUT : ValidateCondition.LIST_CONTAINS_DUPLICATES;
            }
            return ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
        }
    },
    LIST_CONTAINS_NUMBER_OUT_OF_RANGE {
        @Override
        String retrieveMessage() {
            return FAILED_CONTAINING_NUMBER_NOT_IN_RANGE;
        }

        @Override
        ValidateCondition validateCondition(List<Integer> list) {
            if (list != null) {
                int listLength = list.size();
                int listLengthOfValidNumbers = 0;
                for (Integer i : list) {
                    if (isInRange(i)) {
                        listLengthOfValidNumbers++;
                        if (listLengthOfValidNumbers > NUMBERS_TO_DRAW) {
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
            return FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS;
        }

        @Override
        ValidateCondition validateCondition(List<Integer> list) {
            if (list != null) {
                return list.size() == NUMBERS_TO_DRAW ? ValidateCondition.CORRECT_INPUT : ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
            }
            return ValidateCondition.LIST_LESS_OR_BIGGER_THEN_SIX_NUMBERS_OR_NULL;
        }
    };

    private static int countNumbersFromUser(List<Integer> list) {
        return Arrays.stream(list.toArray())
                .collect(Collectors.toSet())
                .size();
    }

    String retrieveMessage() {
        return "No such Enum";
    }

    ValidateCondition validateCondition(List<Integer> list) {
        return ValidateCondition.CORRECT_INPUT;
    }

    boolean isInRange(Integer number) {
        return number >= LOW_NUMBER_BOUNDRY && number <= HIGH_NUMBER_BOUNDRY;
    }

}
