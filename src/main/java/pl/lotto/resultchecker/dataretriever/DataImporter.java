package pl.lotto.resultchecker.dataretriever;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.winningnumbergenerator.WiningNumbersGeneratorFacade;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class DataImporter implements Retrievable {
    private final WiningNumbersGeneratorFacade generatorFacade;
    private final NumberReceiverFacade receiverFacade;

    Clock clock;

    public DataImporter(WiningNumbersGeneratorFacade generatorFacade, NumberReceiverFacade receiverFacade, Clock clock) {
        this.generatorFacade = generatorFacade;
        this.receiverFacade = receiverFacade;
        this.clock = clock;
    }

    @Override
    public List<Integer> fetchWonNumbers() {
        return generatorFacade.retrieveWonNumbersForDate(calculateLastDrawDate());
    }

    @Override
    public List<NumberReceiverResultDto> fetchNewTickets() {
        return receiverFacade.retrieveNumbersForDate(calculateLastDrawDate());
    }

    @Override
    public LocalDateTime calculateLastDrawDate() {
        return receiverFacade.outputDrawTime();
    }

}
