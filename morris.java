import java.util.*;

public class morris {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int iterations = Integer.parseInt(userInput);
        String startString = "1";
        scanner.close();

        for (int i = 0; i < iterations - 1; i++) {
            startString = morris.nextMorris(startString);
        }
        System.out.println(startString);

    }

    public static String nextMorris(String number) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < number.length(); i++) {
            if (i == number.length() - 1 || number.charAt(i) != number.charAt(i + 1)) {
                sb.append(count);
                sb.append(number.charAt(i));
                count = 1;
            } else {
                count++;
            }
        }
        return sb.toString();

    }
}
