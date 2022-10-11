package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.repository.UserInput;

import java.util.Optional;

public class RecordMapper {

    public static UserInput fromDto(NumberReceiverResultDto dto) {
        return new UserInput(dto.message(), dto.userNumbers(), dto.uniqueLotteryId().get(), dto.dateOfDraw().get());
    }

    public static NumberReceiverResultDto toDto(UserInput input) {
        return new NumberReceiverResultDto(input.message(), Optional.of(input.uniqueLotteryID()), input.numbersFromUser(), Optional.of(input.date()));
    }
}
