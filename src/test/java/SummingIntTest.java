import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class SummingIntTest {
    @Test
    void test() {
        var expected = 6;
        var actual =Stream.of(1, 2, 3)
            .collect(Collectors.summingInt(Integer::intValue));

        assertThat(actual).isEqualTo(expected);
    }
}
