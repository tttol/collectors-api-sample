import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummingInt {
    public void run() {
        var sum = Stream.of(1, 2, 3).collect(Collectors.summingInt(Integer::intValue));
        System.out.println(sum);
        // 6
    }
}
