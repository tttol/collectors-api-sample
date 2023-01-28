import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ToUnmodifiableSetTest {
    @Test
    void test() {
        assertThatThrownBy(this::run).isInstanceOf(UnsupportedOperationException.class);
    }

    public void run() {
        Set<String> set = Stream.of("a", "b", "c").collect(Collectors.toSet());
        set.add("d"); //OK
        Set<String> unmodifiableSet = Stream.of("a", "b", "c").collect(Collectors.toUnmodifiableSet());
        unmodifiableSet.add("d"); //UnsupportedOperationException
    }
}
