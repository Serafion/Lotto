package pl.lotto;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import pl.lotto.features.GetNumbersEndpoint;
import pl.lotto.infrastructure.httpclient.resultchecker.NumberProviderClient;
import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest(
        classes = LottoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = MutableClock.class)
@Import(TestConfig.class)
@Testcontainers
@ContextConfiguration(initializers = {WireMockInitializer.class})
@ActiveProfiles("integration")
public class BaseIntegrationTest {


    @Autowired
    public WinnerRepository winnerRepository;
    @Autowired
    public ResultCheckerRepository resultCheckerRepository;
    @Autowired
    NumberProviderClient numberProvider;
    @Autowired
    public WireMockServer wireMockServer;
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

    @Autowired
    GetNumbersEndpoint testtttt;

    @BeforeEach
    void reset() throws JsonProcessingException {
        testtttt.setUpGetNumbersEndpoint(wireMockServer, List.of(1, 2, 3, 4, 5, 6));

        //reset clock for tests
        clock.setToday(LocalDateTime.of(2022, 02, 12, 10, 11, 00).atZone(ZoneId.systemDefault()));
        //reset repository
        winnerRepository.deleteAll();
        resultCheckerRepository.deleteAll();

    }

    @AfterEach
    public void afterEach() {
        this.wireMockServer.resetAll();
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
