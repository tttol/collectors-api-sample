import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class MaxByTest {
  record Entity(String key, String seq) {}

  @Test
  void test() {
      var expected = Map.of(
          "k1", Optional.of(new Entity("k1", "0003")),
          "k2", Optional.of(new Entity("k2", "0002"))
      );
      var actual = run();

      assertThat(actual).isEqualTo(expected);
  }

  private Map<String, Optional<Entity>> run() {
        var entities = List.of(
                new Entity("k1", "0001"),
                new Entity("k1", "0003"),
                new Entity("k1", "0002"),
                new Entity("k2", "0002"),
                new Entity("k2", "0001")
        );

        return entities.stream()
                .collect(Collectors.groupingBy(Entity::key, Collectors.maxBy(Comparator.comparing(Entity::seq))));
    }
}
