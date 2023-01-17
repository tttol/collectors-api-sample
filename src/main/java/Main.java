public class Main {
    public static void main(String[] args) {
        new MaxBy().run();
        new ToCollection();
        try {
            new ToUnmodifiableSet().run();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
        new Joining().run();
        new Mapping().run();
        new FlatMapping().run();
        new Filtering().run();
        new CollectingAndThen().run();
    }
}
