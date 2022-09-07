import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ann", "Bob", "Clark", "Din", "Ell");
        getOddIndexName(names);
        getNamesInUpperCase(names);

        String[] stringArray = new String[]{"1, 2, 0", "4, 5"};
        getSortedIntArray(stringArray);

        Stream<Long> longStream = generator(25214903917L, 11L, 48L, 0);
        longStream.forEach(System.out::println);

        Stream<String> first = Stream.of("one", "two", "three", "four");
        Stream<String> second = Stream.of("1", "2", "3");
        Stream<String> resultStream = zip(first, second);
        resultStream.peek(System.out::println).collect(Collectors.toList());
    }

    //task1
    public static void getOddIndexName(List<String> names) {
        Stream<String> nameStream = names.stream().filter((name) -> names.indexOf(name)%2 == 0);
        System.out.println(nameStream.collect(Collectors.toList()));
    }

    //task2
    private static void getNamesInUpperCase(List<String> names) {
        Stream<String> nameStream = names.stream()
                .map(n -> n.toUpperCase())
                .sorted(Comparator.reverseOrder());
        System.out.println(nameStream.collect(Collectors.toList()));
    }

    //task3
    private static void getSortedIntArray(String[] stringArray) {
        String allString = " ";
        for(String string : stringArray) {
            allString += string + ",";
        }

        System.out.println(allString); // 1, 2, 0, 4, 5,

        List<String> numbers = Arrays.asList(allString.split(","));
        System.out.println(numbers);

        Stream<String> numberStream = numbers.stream()
                .map(String::trim)
                .sorted();

        System.out.println(numberStream.collect(Collectors.toList()));
    }

    //task4
    public static Stream<Long> generator(long a, long c, long m, long seed) {
        final long powM = (long) Math.pow(2, m);
        return Stream.iterate(seed, x -> (a * x + c) % powM)
                .limit(10);
    }

    //task5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> resultList = new ArrayList<>();

        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();

        while (firstIterator.hasNext() && secondIterator.hasNext()) {
            resultList.add(firstIterator.next());
            resultList.add(secondIterator.next());
        }
        return resultList.stream();
    }
}
