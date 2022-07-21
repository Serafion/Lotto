package pl.lotto.numberreceiver.uuidgenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.configuration.Configuration;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UuidGeneratorTest {
    @Test
    @DisplayName("should return random Optional when correct message")
    void shouldReturnRandomOptionalWhenCorrectMessage() {
        //given
        UuidGeneratorModel uuidGeneratorModel = mock(UuidGeneratorModel.class);
        given(uuidGeneratorModel.retriveOptional(Configuration.CORRECT_MESSAGE)).willReturn(Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002")));
        UuidGenerator uuidGenerator = new UuidGenerator(uuidGeneratorModel);

        //when
        Optional<UUID> result = uuidGenerator.generateOptionalUuid(Configuration.CORRECT_MESSAGE);
        //then
        assertThat(Optional.of(UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002"))).isEqualTo(result);
    }

    @Test
    @DisplayName("should return empty Optional when failed message SixNumbers")
    void shouldReturnEmptyOptionalWhenFailedMessageSixNumbers() {
        //given
        UuidGenerator uuidGenerator = new UuidGenerator();
        Optional<UUID> randomUuid = Optional.empty();

        //when
        Optional<UUID> result = uuidGenerator.generateOptionalUuid(Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS);
        //then
        assertThat(randomUuid.isEmpty()).isSameAs(result.isEmpty());
    }

    @Test
    @DisplayName("should return true when checking for random uuid with correct message")
    void shouldReturnTrueWhenRandomUUIDGenerated() {
        //given
        UuidGenerator uuidGenerator = new UuidGenerator();

        //when
        Optional<UUID> result = uuidGenerator.generateOptionalUuid(Configuration.FAILED_DID_NOTE_RECEIVED_EXACTLY_SIX_NUMBERS);
        //then
        assertThat(result.isEmpty()).isTrue();
    }
}