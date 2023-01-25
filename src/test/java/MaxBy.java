import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaxBy {

    public void run() {
        record Entity(String key, String seq) {}
        var entities = List.of(
                new Entity("k1", "0001"),
                new Entity("k1", "0003"),
                new Entity("k1", "0002"),
                new Entity("k2", "0002"),
                new Entity("k2", "0001")
        );

        Map<String, Optional<Entity>> map = entities.stream()
                .collect(Collectors.groupingBy(Entity::key, Collectors.maxBy(Comparator.comparing(Entity::seq))));
        map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));

        // 似たことを他の手法で
        entities.stream()
                .collect(
                    Collectors.toMap(Entity::key,
                            Function.identity(),
                            BinaryOperator.maxBy(Comparator.comparing(Entity::seq))
                    )
                )
                .forEach((key, value) -> System.out.printf("%s=%s%n", key, value));

        entities.stream()
                .collect(Collectors.toMap(Entity::key, Entity::seq, (oldValue, newValue) -> {
                    var oldInt = Integer.parseInt(oldValue);
                    var newInt = Integer.parseInt(newValue);
                    return oldInt <= newInt ? newValue : oldValue;
                }))
                .forEach((key, value) -> System.out.printf("%s=%s%n", key, value));

        entities.stream()
                .collect(Collectors.toMap(Entity::key,
                        Entity::seq,
                        (oldValue, newValue) -> oldValue.compareTo(newValue) <= 0 ? newValue : oldValue)
                )
                .forEach((key, value) -> System.out.printf("%s=%s%n", key, value));;
    }
}
