public class VarargsExample {
    static String firstOfFirst(List<String>... strings) {
        List<Integer> ints = Collections.singletonList(42);
        return strings[0].get(0);
    }
}