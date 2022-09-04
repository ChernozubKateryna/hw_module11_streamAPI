import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ann", "Bob", "Clark", "Din", "Ell");
        getOddIndexName(names);
        getNamesInUpperCase(names);

        String[] stringArray = new String[]{"1, 2, 0", "4, 5"};
        getSortedIntArray(stringArray);


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
}
