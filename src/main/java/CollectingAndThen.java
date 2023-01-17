import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingAndThen {
    public void run() {
        List<String> list = Stream.of("a", "b", "c").collect(
                Collectors.collectingAndThen(Collectors.toList(),
                        e -> e.stream().map(String::toUpperCase).toList())
        );
        list.forEach(System.out::println);
//        A
//        B
//        C
    }
}
