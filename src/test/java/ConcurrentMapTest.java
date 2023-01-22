import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentMapTest {

    @Test
    @DisplayName("Mapの場合")
    void mapTest() throws Exception {
        var map = new HashMap<String, Integer>();
        var sumList = parallelSum100(map);

        assertNotEquals(1, sumList
                .stream()
                .distinct()
                .count());
        var wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertTrue(wrongResultCount > 0);

    }
    @Test
    @DisplayName("ConcurrentHashMapの場合")
    void concurrentMapTest() throws Exception {
        var map = new ConcurrentHashMap<String, Integer>();
        var sumList = parallelSum100(map);

        assertEquals(1, sumList
                .stream()
                .distinct()
                .count());
        var wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertEquals(0, wrongResultCount);
    }

    @Disabled //TODO refactor
    @Test
    @DisplayName("CompletableFuture version")
    void cfTest() throws Exception {
        var targetMap = new HashMap<String, Integer>();
        var actual = doMultiCompletableFuture(targetMap);
        var expected = List.of(
                Map.of("key1", 0),
                Map.of("key1", 0),
                Map.of("key1", 0),
                Map.of("key1", 0),
                Map.of("key1", 0),
                Map.of("key1", 0),
                Map.of("key1", 0),
                Map.of("key1", 0),
                Map.of("key1", 0),
                Map.of("key1", 0)
        );
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private List<Integer> parallelSum100(Map<String, Integer> map) throws InterruptedException {
        List<Integer> sumList = new ArrayList<>(1000);
        for (int i = 0; i < 100; i++) {
            map.put("test", 0);
            var executorService = Executors.newFixedThreadPool(4);
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < 10; k++)
                        map.computeIfPresent(
                                "test",
                                (key, value) -> value + 1
                        );
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add(map.get("test"));
        }
        return sumList;
    }

    public List<Map<String, Integer>> doMultiCompletableFuture(Map<String, Integer> targetMap) throws Exception {
        Supplier<Map<String, Integer>> getMap = () -> targetMap;
        Function<Map<String, Integer>, Map<String, Integer>> putToMap = (map) -> {
            map.put("key1", 0);
            return map;
        };
        var futures = new ArrayList<CompletableFuture<Map<String, Integer>>>();
        for (int i = 0; i < 10; i++) {
            var future = CompletableFuture.supplyAsync(getMap)
                    .thenApplyAsync(putToMap);
            futures.add(future);
        }

        return futures.stream().map(f -> {
            try {
                return f.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

//    public void run() {
//        var map = new HashMap<String, String>();
//        map.put("k1", "v1");
//        var concurrentMap = new ConcurrentHashMap<String, String>();
//        concurrentMap.put("K1", "V1");
//
//        Runnable runnable = () -> System.out.println();
//
//        var t1 = new Thread();
//    }
}
