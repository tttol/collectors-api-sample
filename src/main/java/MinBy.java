import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MinBy {

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
                .collect(Collectors.groupingBy(Entity::key, Collectors.minBy(Comparator.comparing(Entity::seq))));
                map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
    }
}
