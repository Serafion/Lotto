package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class UserInputService implements InputService {
    private final UserInputRepository userInputRepository;

    public UserInputService(UserInputRepository userInputRepository) {
        this.userInputRepository = userInputRepository;
    }

    @Override
    public void addToCurrentNumberList(NumberReceiverResultDto dto) {
        if (dto.uniqueLotteryId().isPresent()) {
            UserInput input = RecordMapper.fromDto(dto);
            userInputRepository.save(input);
        }
    }

    @Override
    public List<NumberReceiverResultDto> provideNumbersForDate(LocalDateTime dateOfDraw) {
        List<NumberReceiverResultDto> convertedList = new ArrayList<>();
        for (UserInput input : userInputRepository.findAllByDate(dateOfDraw)) {
            convertedList.add(RecordMapper.toDto(input));
        }
        return convertedList;
    }

    @Override
    public LocalDateTime provideDrawDate(UUID uuid) {
        return userInputRepository.getDateOfDraw(uuid);
    }
}

