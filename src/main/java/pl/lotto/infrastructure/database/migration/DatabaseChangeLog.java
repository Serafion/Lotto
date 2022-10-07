package pl.lotto.infrastructure.database.migration;//package pl.lotto.infrastructure.database;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import pl.lotto.numberreceiver.UserInput;
import pl.lotto.numberreceiver.UserInputRepository;

@ChangeLog(order = "1")
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "seedDatabase", author = "bartlomiej.kalka")
    @Profile("integration")
    public void seedDatabase1(UserInputRepository ticketRepository) {
        ticketRepository.save(new UserInput("asdasd", List.of(1, 2, 3, 4, 5, 6), UUID.randomUUID(), LocalDateTime.now()));
    }

    @ChangeSet(order = "002", id = "seedDatabase2", author = "bartlomiej.kalka")
    public void seedDatabase2(UserInputRepository ticketRepository) {
        ticketRepository.save(new UserInput("asdasd", List.of(1, 2, 3, 4, 5, 6), UUID.randomUUID(), LocalDateTime.now()));
    }
//    @ChangeSet(order = "002", id = "seedDatabase", author = "bartlomiej.kalka")
//    public void seedDatabase2(UserInputRepository ticketRepository) {
//        ticketRepository.save(new UserInput("asdasd", List.of(1, 2, 3, 4, 5, 6), UUID.randomUUID(), LocalDateTime.now()));
//    }
//    @ChangeSet(order = "003", id = "seedDatabase", author = "bartlomiej.kalka")
//    public void seedDatabase3(UserInputRepository ticketRepository) {
//        ticketRepository.save(new UserInput("asdasd", List.of(1, 2, 3, 4, 5, 6), UUID.randomUUID(), LocalDateTime.now()));
//    }
//    @ChangeSet(order = "004", id = "seedDatabase", author = "bartlomiej.kalka")
//    public void seedDatabase4(UserInputRepository ticketRepository) {
//        ticketRepository.save(new UserInput("asdasd", List.of(1, 2, 3, 4, 5, 6), UUID.randomUUID(), LocalDateTime.now()));
//    }
//    @ChangeSet(order = "005", id = "seedDatabase", author = "bartlomiej.kalka")
//    public void seedDatabase5(UserInputRepository ticketRepository) {
//        ticketRepository.save(new UserInput("asdasd", List.of(1, 2, 3, 4, 5, 6), UUID.randomUUID(), LocalDateTime.now()));
//    }
//    @ChangeSet(order = "006", id = "seedDatabase", author = "bartlomiej.kalka")
//    public void seedDatabase6(UserInputRepository ticketRepository) {
//        ticketRepository.save(new UserInput("asdasd", List.of(1, 2, 3, 4, 5, 6), UUID.randomUUID(), LocalDateTime.now()));
//    }
}