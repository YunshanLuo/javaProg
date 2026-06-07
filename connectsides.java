import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

public class connectsides {
    public static void main(String[] args) throws IOException {
        BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
        String[] dim = buffRead.readLine().split(" ");
        int r = Integer.parseInt(dim[0]);
        int c = Integer.parseInt(dim[1]);

        char[][] matrix = new char[r][c];
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < r; i++) {
            String row = buffRead.readLine();
            for (int j = 0; j < c; j++) {
                char character = row.charAt(j);
                matrix[i][j] = character;

                if (character == 'x') {
                    xCount++;
                } else if (character == 'o') {
                    oCount++;
                }

            }
        }
        if (Math.abs(xCount - oCount) > 1) {
            System.out.println("fusk");
            return;
        }



    }
}
