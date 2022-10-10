package pl.lotto;

import com.github.tomakehurst.wiremock.WireMockServer;
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
import pl.lotto.numberprovider.NumberProviderFacade;
import pl.lotto.resultannouncer.repository.WinnerRepository;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(
        classes = LottoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "application.environment=integration")
//@ContextConfiguration(classes = MutableClock.class)
@Import(TestConfig.class)
@Testcontainers
@ContextConfiguration(initializers = {WireMockInitializer.class})
public class BaseIntegrationTest {


    @Autowired
    public WinnerRepository winnerRepository;
    @Autowired
    public ResultCheckerRepository resultCheckerRepository;
    @Autowired
    NumberProviderFacade numberProviderFacade;
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
    void reset() {
        //
        StringValuePattern pswd = equalTo("abc");
        StringValuePattern date = equalTo("2022-02-12");
        Map<String, StringValuePattern> map = new HashMap<>();
        map.put(date.getName(), date);
        map.put(pswd.getName(), pswd);
//        configureFor("http://numbergetter.pl",0);
        wireMockServer.stubFor(get(urlEqualTo("http://numbergetter.pl/get_numbers/get_numbers?date=2022-02-12&pswd=abc")).willReturn(aResponse().withStatus(200).withBody("{[1,2,3,4,5,6]}")));

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
