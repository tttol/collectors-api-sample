import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToCollection {
    public void run() {
        ArrayList<String> list = Stream.of("a", "b", "c").collect(Collectors.toCollection(ArrayList::new));
        LinkedList<String> linkedList = Stream.of("a", "b", "c").collect(Collectors.toCollection(LinkedList::new));
    }
}
