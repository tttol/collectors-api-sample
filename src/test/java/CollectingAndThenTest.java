import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class CollectingAndThenTest {
    @Test
    void test() {
        var expected = List.of("A", "B", "C");
        var actual = run();
        assertThat(actual).isEqualTo(expected);
    }

    private List<String> run() {
        return Stream.of("a", "b", "c").collect(
                Collectors.collectingAndThen(Collectors.toList(),
                        e -> e.stream().map(String::toUpperCase).toList())
        );
    }
}
