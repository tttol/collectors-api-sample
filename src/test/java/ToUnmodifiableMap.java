import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToUnmodifiableMap {
    public void run() {
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
