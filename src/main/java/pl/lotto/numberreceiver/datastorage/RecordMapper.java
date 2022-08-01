package pl.lotto.numberreceiver.datastorage;

import java.util.Optional;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

class RecordMapper {

    public static UserInput fromDto(NumberReceiverResultDto dto) {
        return new UserInput(dto.message(), dto.userNumbers(), dto.uniqueLotteryId().get());
    }

    public static NumberReceiverResultDto toDto(UserInput input) {
        return new NumberReceiverResultDto(input.message(), Optional.of(input.uniqueLotteryID()), input.numbersFromUser());
    }
}
