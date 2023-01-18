import java.util.stream.Stream;

public class Counting {
    public void run() {
        var count = (Long) Stream.of("a", "b", "c").count();
        System.out.println(count);
        // 3
    }
}
