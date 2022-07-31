package pl.lotto.numberreceiver.datastorage;

import pl.lotto.numberreceiver.dategenerator.DateGenerator;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataStorage {
    private HashMap<LocalDateTime, List<UserInput>> numbersInputedForEachDrawing;
    private List<UserInput> listOfValidInputs;
    private final LocalDateTime currentDrawDate;
    private final Savable saver;
    private final Loadable loader;

    public DataStorage(){
        this.currentDrawDate = LocalDateTime.now();
        this.saver = new NumberSaver();
        this.loader = new NumberLoader();
        loadStoredData();
        loadCurrentInputs(new DateGenerator().retrieveNextDrawDate());
    }
    public DataStorage(LocalDateTime drawDate){
        this.currentDrawDate = drawDate;
        this.saver = new NumberSaver();
        this.loader = new NumberLoader();
        loadStoredData();
        loadCurrentInputs(drawDate);
    }
    public DataStorage(LocalDateTime drawDate, Savable saver, Loadable loader) {
        this.currentDrawDate = drawDate;
        this.saver = saver;
        this.loader = loader;
        loadStoredData();
        loadCurrentInputs(drawDate);
    }

    public void addToCurrentNumberList(NumberReceiverResultDto dto) {
        if (dto.uniqueLotteryId().isPresent()) {
            RecordConverter converter = new RecordConverter();
            UserInput input = converter.convertRecordToUserInput(dto);
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
        RecordConverter converter = new RecordConverter();
        for (UserInput input : list) {
            converted.add(converter.convertUserInputToDto(input));
        }
        return converted;
    }

    public void closeStorage() {
        numbersInputedForEachDrawing.put(currentDrawDate, listOfValidInputs);
        saver.saveToFile(numbersInputedForEachDrawing);
    }

    private void loadStoredData() {
        numbersInputedForEachDrawing = loader.loadDatabase();
    }

    private void loadCurrentInputs(LocalDateTime drawTime) {
        if (numbersInputedForEachDrawing.containsKey(drawTime)) {
            listOfValidInputs = numbersInputedForEachDrawing.get(drawTime);
        } else {
            listOfValidInputs = new ArrayList<>();
        }
    }

}

