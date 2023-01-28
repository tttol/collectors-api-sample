import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class ToMapTest {
    record Person(String name, String city, int height){}

    @Test
    void test() {
        var expected = Map.of(
            "LA", "Michael",
            "NY", "John"
        );
        var actual = run();

        assertThat(actual).isEqualTo(expected);
    }

    private Map<String, String> run() {

        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

        return people.stream().collect(Collectors.toMap(
                Person::city,
                Person::name,
                (oldValue, newValue) -> newValue // key重複時は後勝ちでPUT
        ));

    }
}
