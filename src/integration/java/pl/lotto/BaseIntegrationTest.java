package pl.lotto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(
        classes = LottoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "application.environment=integration")
//@ContextConfiguration(classes = MutableClock.class)
@Import(TestConfig.class)
@Testcontainers
public class BaseIntegrationTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    public TestRestTemplate testRestTemplate;

    @Autowired
    public MutableClock clock;

    @BeforeEach
    void reset() {
        //reset clock for tests
        clock.setToday(LocalDateTime.of(2022, 02, 12, 10, 11, 00).atZone(ZoneId.systemDefault()));
    }

//    @Bean
//    ZonedDateTime zonedDateTime(){
//      return LocalDateTime.of(2022,02,12,10,11,00).atZone(ZoneId.systemDefault());
//    }

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

//    @Bean
//    @Primary
//    public Clock mutableClock(){
//        return new MutableClock(LocalDateTime.of(2022,02,12,10,11,00).atZone(ZoneId.systemDefault()));
//    }
}
