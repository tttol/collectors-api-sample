import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class FlatMappingTest {
  record Product (String name, int value) {}
  record Selling (String clientName, List<Product> products){}

  @Test
  void test() {
    var expected = Map.of(
        "clientC", List.of(new Product("p3-1", 31), new Product("p3-2", 32)),
        "clientB", List.of(new Product("p2-1", 21), new Product("p2-2", 22), new Product("p2-3", 23), new Product("p2-4", 24)),
        "clientA", List.of(new Product("p1-1", 11), new Product("p1-2", 12), new Product("p1-3", 13)
        )
    );
    var actual = run();

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  private Map<String, List<Product>> run() {

        var sellings = List.of(
                new Selling("clientA", List.of(
                        new Product("p1-1", 11),
                        new Product("p1-2", 12),
                        new Product("p1-3", 13)
                    )
                ),
                new Selling("clientB", List.of(
                        new Product("p2-1", 21),
                        new Product("p2-2", 22),
                        new Product("p2-3", 23),
                        new Product("p2-4", 24)
                    )
                ),
                new Selling("clientC", List.of(
                        new Product("p3-1", 31),
                        new Product("p3-2", 32)
                    )
                )
        );
        return sellings.stream()
                .collect(Collectors.groupingBy(Selling::clientName,
                        Collectors.flatMapping(selling -> selling.products.stream(), Collectors.toList())));
    }
}
