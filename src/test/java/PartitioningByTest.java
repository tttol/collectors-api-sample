import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class PartitioningByTest {
    record Person(String name, String city, int height){}

    @Test
    void test() {
        var expected = Map.of(
            false, 2L,
            true, 2L
        );
        var actual = run();

        assertThat(actual).isEqualTo(expected);
    }
    private Map<Boolean, Long> run() {

        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

        return people.stream().collect(Collectors.partitioningBy(person -> person.height >= 175, Collectors.counting()));
        // false=2
        // true=2
    }
}
