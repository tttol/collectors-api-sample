public class Main {
    public static void main(String[] args) {
        new MaxBy().run();
        System.out.println("----------------");
        new ToCollection();
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
    }
}
