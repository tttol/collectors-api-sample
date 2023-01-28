import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class JoiningTest {
    @Test
    void test() {
        var expected = "[a,b,c]";
        var actual = Stream.of("a", "b", "c")
            .collect(Collectors.joining(",", "[", "]"));
        assertThat(actual).isEqualTo(expected);
    }
}
