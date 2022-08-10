package pl.lotto.numberreceiver.dategenerator;

import java.time.LocalDateTime;

public interface Clockable {
    LocalDateTime retrieveNextDrawDate();
}
