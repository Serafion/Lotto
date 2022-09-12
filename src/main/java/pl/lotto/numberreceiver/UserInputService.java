package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

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
        Optional<UserInput> byId = userInputRepository.findById(uuid);
        UserInput userInput = byId.get();
        return userInput.date();
    }
}

