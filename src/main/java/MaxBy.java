import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaxBy {
    private record Entity(String yearAndModel, String seq) {}

    public void run() {
        var entities = List.of(
                new Entity("22-H", "0001"),
                new Entity("22-H", "0003"),
                new Entity("22-H", "0002"),
                new Entity("23-H", "0002"),
                new Entity("23-H", "0001")
        );

        entities.stream()
                .collect(
                    Collectors.toMap(Entity::yearAndModel,
                            Function.identity(),
                            BinaryOperator.maxBy(Comparator.comparing(Entity::seq))
                    )
                )
                .forEach((key, value) -> System.out.printf("%s=%s%n", key, value));

        entities.stream()
                .collect(Collectors.toMap(Entity::yearAndModel, Entity::seq, (oldValue, newValue) -> {
                    var oldInt = Integer.parseInt(oldValue);
                    var newInt = Integer.parseInt(newValue);
                    return oldInt <= newInt ? newValue : oldValue;
                }))
                .forEach((key, value) -> System.out.printf("%s=%s%n", key, value));

        entities.stream()
                .collect(Collectors.toMap(Entity::yearAndModel,
                        Entity::seq,
                        (oldValue, newValue) -> oldValue.compareTo(newValue) <= 0 ? newValue : oldValue)
                )
                .forEach((key, value) -> System.out.printf("%s=%s%n", key, value));;
    }
}
