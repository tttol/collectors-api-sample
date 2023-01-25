import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ToConcurrentMap {
    public void run() {
        record Person(String name, String city, int height){}

        var people = List.of(
                new Person("Tom", "NY", 170),
                new Person("John", "NY", 180),
                new Person("Bob", "LA", 175),
                new Person("Michael", "LA", 172)
        );

        ConcurrentMap<String, String> map = people.stream().collect(Collectors.toConcurrentMap(
                Person::city,
                Person::name,
                (oldValue, newValue) -> newValue // key重複時は後勝ちでPUT
        ));
        map.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
        // LA=Michael
        // NY=John
    }
}
