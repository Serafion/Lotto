package pl.lotto.resultchecker;

import java.util.UUID;
import static pl.lotto.TestConstants.sampleUuid;

public interface SampleUserUuid {

    default UUID sampleUserUuid() {
        return sampleUuid;
    }
}
