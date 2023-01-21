import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AveragingInt {
    public void run() {
        var ave = Stream.of(1, 2, 3).collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(ave);
        // 2.0
    }
}
