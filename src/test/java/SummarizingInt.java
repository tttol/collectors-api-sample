import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummarizingInt {
    public void run() {
        IntSummaryStatistics intSummaryStatistics = Stream.of(1, 2, 3).collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(intSummaryStatistics);
        // IntSummaryStatistics{count=3, sum=6, min=1, average=2.000000, max=3}
    }
}
