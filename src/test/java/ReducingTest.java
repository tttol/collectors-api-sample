import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class ReducingTest {
    record Person(String name, String city, int height){}

    @Test
    void reduceWithOperatorTest() {
        // LA=Optional[Person[name=Bob, city=LA, height=175]]
        // NY=Optional[Person[name=John, city=NY, height=180]]

        var expected = Map.of(
            "LA", Optional.of(new Person("Bob", "LA", 175)),
            "NY", Optional.of(new Person("John", "NY", 180))
        );
        var actual = reduceWithOperator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void reduceWithIdentityAndMapperTest() {
        // LA=Michael
        // NY=John
        var expected = Map.of(
            "LA", "Michael",
            "NY", "John"
        );
        var actual = reduceWithIdentityAndMapper();

        assertThat(actual).isEqualTo(expected);
    }

    private Map<String, Optional<Person>> reduceWithOperator() {
        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

        var byHeight = Comparator.comparing(Person::height);
        return people.stream().collect(
                Collectors.groupingBy(Person::city,
                        Collectors.reducing(BinaryOperator.maxBy(byHeight))));
    }

    private Map<String, String> reduceWithIdentityAndMapper() {
        var people = List.of(
            new Person("Tom", "NY", 170),
            new Person("John", "NY", 180),
            new Person("Bob", "LA", 175),
            new Person("Michael", "LA", 172)
        );

        var byLength = Comparator.comparing(String::length);
        return people.stream().collect(
            Collectors.groupingBy(Person::city,
                Collectors.reducing("",
                    Person::name,
                    BinaryOperator.maxBy(byLength))));
    }
}
