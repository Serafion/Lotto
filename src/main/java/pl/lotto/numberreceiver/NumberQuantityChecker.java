package pl.lotto.numberreceiver;

import java.util.List;

import static pl.lotto.numberreceiver.Constants.NUMBERS_TO_DRAW;

class NumberQuantityChecker implements Conditionable {
    @Override
    public ValidateMessage validateCondition(List<Integer> list) {
        return list.size() == NUMBERS_TO_DRAW ?
                ValidateMessage.CORRECT_MESSAGE :
                ValidateMessage.NOT_SIX_NUMBERS;
    }
}
