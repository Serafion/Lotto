package pl.lotto.numberreceiver.repository;

import java.time.LocalDateTime;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class UserInputService implements InputService {
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
}

