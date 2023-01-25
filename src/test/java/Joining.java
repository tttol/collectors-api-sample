import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Joining {
    public void run() {
        var s1 = Stream.of("a", "b", "c");
        var s2 = Stream.of("a", "b", "c");
        var s3 = Stream.of("a", "b", "c");
        var j1 = s1.collect(Collectors.joining());
        var j2 = s2.collect(Collectors.joining(","));
        var j3 = s3.collect(Collectors.joining(",", "[", "]"));

        System.out.println(j1);
        System.out.println(j2);
        System.out.println(j3);
    }
}
