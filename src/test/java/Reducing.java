import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Reducing {
    public void run() {
        record Person(String name, String city, int height){}

        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

//        var byLengthReversed = Comparator.comparing(String::length).reversed();
//        var shortestNameByCity = people.stream().collect(
//                Collectors.groupingBy(
//                        e -> e,
//                        Collectors.reducing("",
//                                BinaryOperator.maxBy(byLengthReversed))
//                        )
//        );
//        shortestNameByCity.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));

        var byHeight = Comparator.comparing(Person::height);
        Map<String, Optional<Person>> tallestByCity = people.stream().collect(
                Collectors.groupingBy(Person::city,
                        Collectors.reducing(BinaryOperator.maxBy(byHeight))));
        tallestByCity.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
        // LA=Optional[Person[name=Bob, city=LA, height=175]]
        // NY=Optional[Person[name=John, city=NY, height=180]]

        var byLength = Comparator.comparing(String::length);
        Map<String, String> longestNameByCity = people.stream().collect(
                Collectors.groupingBy(Person::city,
                        Collectors.reducing("",
                                Person::name,
                                BinaryOperator.maxBy(byLength))));
        longestNameByCity.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
        // LA=Michael
        // NY=John

    }
}
