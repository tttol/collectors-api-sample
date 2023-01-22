public class Main {
    public static void main(String[] args) {
        new ToCollection();
        System.out.println("----------------");
        try {
            new ToUnmodifiableSet().run();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------");
        new Joining().run();
        System.out.println("----------------");
        new Mapping().run();
        System.out.println("----------------");
        new FlatMapping().run();
        System.out.println("----------------");
        new Filtering().run();
        System.out.println("----------------");
        new CollectingAndThen().run();
        System.out.println("----------------");
        new Counting().run();
        System.out.println("----------------");
        new MinBy().run();
        System.out.println("----------------");
        new MaxBy().run();
        System.out.println("----------------");
        new SummingInt().run();
        System.out.println("----------------");
        new AveragingInt().run();
        System.out.println("----------------");
        new Reducing().run();
        System.out.println("----------------");
        new GroupingBy().run();
        System.out.println("----------------");
        new PartitioningBy().run();
        System.out.println("----------------");
        new ToMap().run();
        System.out.println("----------------");
        try {
            new ToUnmodifiableMap();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------");
        new ToConcurrentMap().run();
        System.out.println("----------------");
        new SummarizingInt().run();
        System.out.println("----------------");
    }
}
