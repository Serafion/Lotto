package pl.lotto.numberreceiver.datastorage;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.Optional;

class RecordConverter {
    public UserInput convertRecordToUserInput(NumberReceiverResultDto dto){
        return new UserInput(dto.message(),dto.userNumbers(),dto.uniqueLotteryId().get());
    }
    public NumberReceiverResultDto convertUserInputToDto(UserInput input){
        return new NumberReceiverResultDto(input.message(), Optional.of(input.uniqueLotteryID()),input.numbersFromUser());
    }
}
