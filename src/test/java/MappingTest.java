import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class MappingTest {
  record Person(String name, int age, String sex) {}

  @Test
  void test() {
    var expected = Map.of(
        "Female", List.of("Jessy", "Hanako"),
        "Male", List.of("Tom", "John")
    );
    var actual = run();

    assertThat(actual).isEqualTo(expected);
  }

  private Map<String, List<String>> run() {
        var persons = List.of(
                new Person("Tom", 20, "Male"),
                new Person("John", 22, "Male"),
                new Person("Jessy", 20, "Female"),
                new Person("Hanako", 30, "Female")
        );

        return persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::sex,
                                Collectors.mapping(Person::name, Collectors.toList())
                        )
                );
    }
}
