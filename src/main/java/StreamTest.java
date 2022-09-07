import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ann", "Bob", "Clark", "Din", "Ell");
        System.out.println(getOddIndexName(names));

        System.out.println(getNamesInUpperCase(names));

        String[] stringArray = new String[]{"1, 2, 0", "4, 5"};
        System.out.println(getSortedIntArray(stringArray));

        Stream<Long> longStream = generator(25214903917L, 11L, 48L, 0);
        longStream.forEach(System.out::println);

        Stream<String> first = Stream.of("one", "two", "three", "four");
        Stream<String> second = Stream.of("1", "2", "3");
        Stream<String> resultStream = zip(first, second);
        resultStream.peek(System.out::println).collect(Collectors.toList());
    }

    //task1
    public static String getOddIndexName(List<String> names) {
        Stream<String> nameStream = names.stream().filter((name) -> names.indexOf(name)%2 == 0);
        List<String> resultList = nameStream.collect(Collectors.toList());
        String result = resultList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "\"", "\""));
        return result;
    }

    //task2
    private static List<String> getNamesInUpperCase(List<String> names) {
        Stream<String> nameStream = names.stream()
                .map(n -> n.toUpperCase())
                .sorted(Comparator.reverseOrder());
        List<String> result = nameStream.collect(Collectors.toList());
        return result;
    }

    //task3
    private static String getSortedIntArray(String[] stringArray) {
        List<String> stringStream = Arrays.stream(stringArray)
                .map(s -> s.split(", "))
                .flatMap(Arrays::stream)
                .map(String::trim)
                .sorted()
                .collect(Collectors.toList());

        return stringStream.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "\"", "\""));
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
