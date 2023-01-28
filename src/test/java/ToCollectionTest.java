import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ToCollectionTest {
    @Test
    void test() {
        var actual = Stream.of("a", "b", "c").collect(Collectors.toCollection(ArrayList::new));
        assertThat(actual).isEqualTo(List.of("a", "b", "c"));
    }
}
