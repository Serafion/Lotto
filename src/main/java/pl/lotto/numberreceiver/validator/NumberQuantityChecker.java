package pl.lotto.numberreceiver.validator;

import java.util.List;

import static pl.lotto.numberreceiver.util.Constants.*;

class NumberQuantityChecker implements Conditionable {
    @Override
    public ValidateMessage validateCondition(List<Integer> list) {
        return list.size() == NUMBERS_TO_DRAW ? ValidateMessage.CORRECT_MESSAGE : ValidateMessage.NOT_SIX_NUMBERS;
    }
}
