import java.io.*;
import java.util.ArrayList;

public class longestsequence {
    public static void main(String[] args) throws IOException{
        // Input parsing
        BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> inputList = new ArrayList<>();

        String line = buffRead.readLine();

        String[] stringNums = line.split(" ");

        int stringLen = stringNums.length;
        int[] arr = new int[stringLen];

        for (int i = 0; i < stringLen; i++) {
            arr[i] = Integer.parseInt(stringNums[i]);
        }

        // Sliding window problem

        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < stringLen; right++) {
            int currNum = arr[right];

            for (int i = left; i < right; i++) {
                if (arr[i] == currNum) {
                    left = i + 1;
                    break;
                }
            }
            int currWindow = right - left + 1;
            if (currWindow > maxLen) {
                maxLen = currWindow;
            }
        }
        System.out.println(maxLen);
    }
    
}