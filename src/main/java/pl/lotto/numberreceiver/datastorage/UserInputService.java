package pl.lotto.numberreceiver.datastorage;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pl.lotto.numberreceiver.dategenerator.DateGenerator;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

public class UserInputService {
//    private Map<Long, List<UserInput>> numbersInputedForEachDrawing;
    private List<UserInput> listOfValidInputs;
//    private final LocalDateTime currentDrawDate;
    private final Clock currentDrawDate;
//    private final Savable saver;
//    private final Loadable loader;

    private final UserInputRepositoryInterface userInputRepositoryInterface;
    private final DateGenerator dateGenerator;

    UserInputService(Clock currentDrawDate, UserInputRepositoryInterface userInputRepositoryInterface, DateGenerator dateGenerator) {
        this.currentDrawDate = currentDrawDate;
        this.userInputRepositoryInterface = userInputRepositoryInterface;
        this.dateGenerator = dateGenerator;
    }

    //    public UserInputRepository() {
//        this.currentDrawDate = LocalDateTime.now();
//        this.saver = new NumberSaver();
//        this.loader = new NumberLoader();
//        loadStoredData();
//        loadCurrentInputs(new DateGenerator().retrieveNextDrawDate());
//    }
//
//    public UserInputRepository(LocalDateTime drawDate) {
//        this.currentDrawDate = drawDate;
//        this.saver = new NumberSaver();
//        this.loader = new NumberLoader();
//        loadStoredData();
//        loadCurrentInputs(drawDate);
//    }
//
//    public UserInputRepository(LocalDateTime drawDate, Savable saver, Loadable loader) {
//        this.currentDrawDate = drawDate;
//        this.saver = saver;
//        this.loader = loader;
//        loadStoredData();
//        loadCurrentInputs(drawDate);
//    }

    public void addToCurrentNumberList(NumberReceiverResultDto dto) {
        if (dto.uniqueLotteryId().isPresent()) {
//            RecordMapper converter = new RecordMapper();
            UserInput input = RecordMapper.fromDto(dto);
            listOfValidInputs.add(input);
        }
    }

    public List<NumberReceiverResultDto> provideNumbersForDate(LocalDateTime dateTime) {
        if (dateTime.equals(currentDrawDate)) {
            return convertData(listOfValidInputs);
        } else {
            return convertData(numbersInputedForEachDrawing.get(dateTime));
        }
    }

    public List<NumberReceiverResultDto> convertData(List<UserInput> list) {
        List<NumberReceiverResultDto> converted = new ArrayList<>();
        RecordMapper converter = new RecordMapper();
        for (UserInput input : list) {
            converted.add(converter.toDto(input));
        }
        return converted;
    }

    public void closeStorage() {
        numbersInputedForEachDrawing.put(currentDrawDate.instant().toEpochMilli(), listOfValidInputs);

        userInputRepositoryInterface.save(numbersInputedForEachDrawing);
    }

    private void loadStoredData() {
        numbersInputedForEachDrawing = loader.fetch();
    }

    private void loadCurrentInputs(LocalDateTime drawTime) {
        if (numbersInputedForEachDrawing.containsKey(drawTime)) {
            listOfValidInputs = numbersInputedForEachDrawing.get(drawTime);
        } else {
            listOfValidInputs = new ArrayList<>();
        }
    }

}

