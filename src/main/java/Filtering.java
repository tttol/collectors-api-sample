import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Filtering {
    public void run() {
        record Employee (String name, String department, int salary) {}

        var employees = List.of(
                new Employee("Tom", "d1", 10000),
                new Employee("John", "d2", 20000),
                new Employee("Michael", "d2", 20000),
                new Employee("Bob", "d3", 30000)
        );

        Map<String, List<Employee>> filtered = employees.stream().collect(Collectors.groupingBy(
                Employee::department, Collectors.filtering(e -> e.salary >= 20000, Collectors.toList())));
        filtered.forEach((k, v) -> System.out.printf("%s=%s%n", k, v));
//        d1=[]
//        d2=[Employee[name=John, department=d2, salary=20000], Employee[name=Michael, department=d2, salary=20000]]
//        d3=[Employee[name=Bob, department=d3, salary=30000]]
    }
}
