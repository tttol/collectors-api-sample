import static org.assertj.core.api.Assertions.assertThat;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class SummarizingIntTest {
    @Test
    void test() {
        var expected = new IntSummaryStatistics(3L, 1, 3, 6L);
        var actual = run();

        assertThat(actual.getAverage()).isEqualTo(expected.getAverage());
        assertThat(actual.getCount()).isEqualTo(expected.getCount());
        assertThat(actual.getMax()).isEqualTo(expected.getMax());
        assertThat(actual.getMin()).isEqualTo(expected.getMin());
        assertThat(actual.getSum()).isEqualTo(expected.getSum());
    }
    private IntSummaryStatistics run() {
        return Stream.of(1, 2, 3).collect(Collectors.summarizingInt(Integer::intValue));
        // IntSummaryStatistics{count=3, sum=6, min=1, average=2.000000, max=3}
    }
}
