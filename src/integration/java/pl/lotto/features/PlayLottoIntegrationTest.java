package pl.lotto.features;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.lotto.BaseIntegrationTest;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayLottoIntegrationTest extends BaseIntegrationTest {

    @Test
    public void f() {
        // given
        String path = "/hello";

        // when
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity(path, String.class);

        // then
        String body = forEntity.getBody();
        HttpStatus status = forEntity.getStatusCode();
        assertThat(body).isEqualTo("Hello");
        assertThat(status).isEqualTo(HttpStatus.OK);


        // given
        String path = "/hello";

        // when
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity(path, String.class);

        // then
        String body = forEntity.getBody();
        HttpStatus status = forEntity.getStatusCode();
        assertThat(body).isEqualTo("Hello");
        assertThat(status).isEqualTo(HttpStatus.OK);
    }
}
