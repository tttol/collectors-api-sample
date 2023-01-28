import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class ToUnmodifiableMapTest {
    @Test
    void test() {
        assertThatThrownBy(this::run).isInstanceOf(UnsupportedOperationException.class);
    }

    private void run() {
        record Person(String name, String city, int height){}

        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

        Map<String, String> map = people.stream().collect(Collectors.toUnmodifiableMap(
                Person::city,
                Person::name,
                (oldValue, newValue) -> newValue // key重複時は後勝ちでPUT
        ));
        map.put("hoge", "fuga");
    }
}
