import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        // Przykład 1: n -> 2 * n
        Series<Integer> intSeries = new Series<>(n -> 2 * n, 5);
        for (Integer value : intSeries) {
            System.out.println(value);
        }

        System.out.println();

        // Przykład 2: n -> "a" * n
        Series<String> stringSeries = new Series<>(n -> String.join("", Collections.nCopies(n, "a")), 5);
        for (String value : stringSeries) {
            System.out.println(value);
        }
    }
}
