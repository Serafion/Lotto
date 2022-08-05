package pl.lotto.numberreceiver.repository;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.Optional;

class RecordMapper {

    public static UserInput fromDto(NumberReceiverResultDto dto) {
        return new UserInput(dto.message(), dto.userNumbers(), dto.uniqueLotteryId().get(), dto.dateOfDraw().get());
    }

    public static NumberReceiverResultDto toDto(UserInput input) {
        return new NumberReceiverResultDto(input.message(), Optional.of(input.uniqueLotteryID()), input.numbersFromUser(), Optional.of(input.drawDate()));
    }
}
