import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class CountingTest {
    @Test
    void test() {
        var expected = 3L;
        var actual = run();
        assertThat(actual).isEqualTo(expected);
    }

    private Long run() {
        return Stream.of("a", "b", "c").count();
    }
}
