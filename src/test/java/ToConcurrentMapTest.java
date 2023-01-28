import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class ToConcurrentMapTest {
    record Person(String name, String city, int height){}
    @Test
    void test() {
        var expected = new ConcurrentHashMap<String, String>();
        expected.put("LA", "Michael");
        expected.put("NY", "John");
        var actual = run();

        assertThat(actual).isEqualTo(expected);
    }
    private ConcurrentMap<String, String> run() {

        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

        return people.stream().collect(Collectors.toConcurrentMap(
                Person::city,
                Person::name,
                (oldValue, newValue) -> newValue // key重複時は後勝ちでPUT
        ));
    }
}
