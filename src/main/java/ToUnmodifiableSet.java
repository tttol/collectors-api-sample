import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToUnmodifiableSet {
    public void run() {
        Set<String> set = Stream.of("a", "b", "c").collect(Collectors.toSet());
        set.add("d"); //OK
        Set<String> unmodifiableSet = Stream.of("a", "b", "c").collect(Collectors.toUnmodifiableSet());
        unmodifiableSet.add("d"); //UnsupportedOperationException
    }
}
