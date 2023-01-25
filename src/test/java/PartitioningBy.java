import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningBy {
    public void run() {
        record Person(String name, String city, int height){}

        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

        Map<Boolean, List<Person>> partitioningBy1 = people.stream()
                .collect(Collectors.partitioningBy(person -> person.height >= 175));
        partitioningBy1.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
        // false=[Person[name=Tom, city=NY, height=170], Person[name=Michael, city=LA, height=172]]
        // true=[Person[name=John, city=NY, height=180], Person[name=Bob, city=LA, height=175]]

        Map<Boolean, Long> partitioningBy2 = people.stream()
                .collect(Collectors.partitioningBy(person -> person.height >= 175, Collectors.counting()));
        partitioningBy2.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
        // false=2
        // true=2
    }
}
