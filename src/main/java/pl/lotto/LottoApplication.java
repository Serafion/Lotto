package pl.lotto;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.Clock;


@SpringBootApplication
@EnableMongoRepositories
@EnableMongock
public class LottoApplication {

    Clock clock = Clock.systemDefaultZone();

    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);
    }

}
