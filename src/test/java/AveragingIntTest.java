import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class AveragingIntTest {
    @Test
    void test() {
        var expected = 2L;
        var actual = run();
        assertThat(actual).isEqualTo(expected);
    }
    private Double run() {
        return Stream.of(1, 2, 3).collect(Collectors.averagingInt(Integer::intValue));
        // 2.0
    }
}
