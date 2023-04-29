import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        int[] numbers = { 1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24 };
        String result = getNumberRanges(numbers);
        System.out.println(result); // output: 1-3,5-7,9-10
    }

    public static String getNumberRanges(int[] numbers) {
        Map<Integer, List<Integer>> ranges = Arrays.stream(numbers)
                .sorted()
                .boxed()
                .collect(Collectors.groupingBy(
                        n -> n - Arrays.binarySearch(numbers, n),
                        Collectors.toList()));

        return ranges.entrySet().stream()
                .map(entry -> {
                    List<Integer> range = entry.getValue();
                    if (range.size() == 1) {
                        return range.get(0).toString();
                    } else {
                        return range.get(0) + "-" + range.get(range.size() - 1);
                    }
                })
                .collect(Collectors.joining(","));
    }
}
