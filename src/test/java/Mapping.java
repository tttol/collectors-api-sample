import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mapping {
    public void run() {
        record Person(String name, int age, String sex) {}

        var persons = List.of(
                new Person("Tom", 20, "Male"),
                new Person("John", 22, "Male"),
                new Person("Jessy", 20, "Female"),
                new Person("Hanako", 30, "Female")
        );

        Map<String, List<String>> mappedToList = persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::sex,
                                Collectors.mapping(Person::name, Collectors.toList())
                        )
                );
        mappedToList.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        //Female=[Jessy, Hanako]
        //Male=[Tom, John]

        Map<String, String> mappedToString = persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::sex,
                                Collectors.mapping(Person::name, Collectors.joining("|"))
                        )
                );
        mappedToString.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        //Female=Jessy|Hanako
        //Male=Tom|John
    }
}
