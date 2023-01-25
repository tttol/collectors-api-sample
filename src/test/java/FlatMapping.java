import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlatMapping {
    public void run() {
        record Product (String name, int value) {}
        record Selling (String clientName, List<Product> products){}

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
        Map<String, List<Product>> flatMapped = sellings.stream()
                .collect(Collectors.groupingBy(Selling::clientName,
                        Collectors.flatMapping(selling -> selling.products.stream(), Collectors.toList())));
        flatMapped.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));

        Map<String, List<List<Product>>> mapped = sellings.stream()
                .collect(Collectors.groupingBy(Selling::clientName,
                        Collectors.mapping(Selling::products, Collectors.toList())));
        mapped.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
    }
}
