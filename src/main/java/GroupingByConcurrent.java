import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class GroupingByConcurrent {
    public void run() {
        record Person(String name, String city, int height){}

        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

        ConcurrentMap<String, List<Person>> groupingBy1 = people.stream().collect(Collectors.groupingByConcurrent(Person::city));
        groupingBy1.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
        // LA=[Person[name=Bob, city=LA, height=175], Person[name=Michael, city=LA, height=172]]
        // NY=[Person[name=Tom, city=NY, height=170], Person[name=John, city=NY, height=180]]
    }
}
