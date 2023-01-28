import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class FilteringTest {
    record Employee (String name, String department, int salary) {}

    @Test
    void test() {
        Map<String, List<Employee>> expected = Map.of(
            "d1", List.of(),
            "d2", List.of(new Employee("John", "d2", 20000), new Employee("Michael", "d2", 20000)),
            "d3", List.of(new Employee("Bob", "d3", 30000))
        );

        var actual = run();

        assertThat(actual).isEqualTo(expected);
    }

    private Map<String, List<Employee>> run() {
        var employees = List.of(
                new Employee("Tom", "d1", 10000),
                new Employee("John", "d2", 20000),
                new Employee("Michael", "d2", 20000),
                new Employee("Bob", "d3", 30000)
        );

        return employees.stream().collect(Collectors.groupingBy(
                Employee::department, Collectors.filtering(e -> e.salary >= 20000, Collectors.toList())));
    }
}
