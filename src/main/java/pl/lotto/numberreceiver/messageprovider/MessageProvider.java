package pl.lotto.numberreceiver.messageprovider;

import pl.lotto.numberreceiver.dategenerator.DateGenerator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static pl.lotto.numberreceiver.constants.Constants.*;

public class MessageProvider {

    private final DateGenerator dateGenerator;
    public MessageProvider() {
        this.dateGenerator = new DateGenerator();
    }
    public String provideMessage(List<Integer> list, Optional<UUID> uuid, String validationMessage){
            return uuid.isPresent() ? fullUserMessage(list, uuid) : validationMessage;
    }

    private String fullUserMessage(List<Integer> list, Optional<UUID> uuid) {
        return FINAL_MESSAGE_PART_1 + list.toString() + FINAL_MESSAGE_PART_2 + uuid.get() + FINAL_MESSAGE_PART_3 + dateGenerator.retrieveNextDrawDate();
    }
}
