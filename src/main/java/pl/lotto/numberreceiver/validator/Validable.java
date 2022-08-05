package pl.lotto.numberreceiver.validator;

import java.util.List;

public interface Validable {
    ValidateMessage retrieveMessageForGivenInput(List<Integer> numbersFromUser);
}
