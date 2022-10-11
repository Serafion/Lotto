package pl.lotto;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Body;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import pl.lotto.numberprovider.NumberProviderClient;
import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(
        classes = LottoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "application.environment=integration")
@Import(TestConfig.class)
@Testcontainers
@ContextConfiguration(initializers = {WireMockInitializer.class})
public class BaseIntegrationTest {


    @Autowired
    public WinnerRepository winnerRepository;
    @Autowired
    public ResultCheckerRepository resultCheckerRepository;
    @Autowired
    NumberProviderClient numberProvider;
    @Autowired
    private WireMockServer wireMockServer;
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
    void reset() throws JsonProcessingException {
        //
        StringValuePattern pswd = equalTo("abc");
        StringValuePattern date = equalTo("2022-02-12");
        Map<String, StringValuePattern> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        map.put(date.getName(), date);
        map.put(pswd.getName(), pswd);
        wireMockServer.stubFor(get("/get_numbers?date=2022-02-12&pswd=abc").willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withJsonBody(Body.fromJsonBytes(objectMapper.writeValueAsBytes(List.of(1, 2, 3, 4, 5, 6))).asJson()).withStatusMessage("it worked somehow")));

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

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

}
