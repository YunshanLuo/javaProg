import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.Queue;
import java.util.ArrayDeque;

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
        // Fusk om skillnaden är större än 1
        if (Math.abs(xCount - oCount) > 1) {
            System.out.println("fusk");
            return;
        }

        // X har vunnit
        boolean xWon = checkWinX(matrix, r, c);

        if (xWon) {
            System.out.println("x");
            return;
        }

    }

    // Har x vunnit dvs. det finns en väg från top till botten
    public static boolean checkWinX(char[][] board, int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < c; i++) {
            if (board[0][i] == 'x') {
                int[] xCoord = new int[]{0, i};
                visited[0][i] = true;
                queue.offer(xCoord);
            }
        }

        while (!queue.isEmpty()) {
            int[] currCord = queue.poll();
            int currRow = currCord[0];
            int currCol = currCord[1];

            if (currRow + 1 == r) {
                return true;
            }
            // Kolla en rad nedåt 
            int changedRow = currRow + 1;
            if (board[changedRow][currCol] == 'x' && !visited[changedRow][currCol]) {
                visited[changedRow][currCol] = true;
                int[] newNode = new int[]{changedRow, currCol};
                queue.offer(newNode);
            }
            // Kolla uppåt
            changedRow = currRow - 1;
            if (board[changedRow][currCol] == 'x' && !visited[changedRow][currCol]) {
                visited[changedRow][currCol] = true;
                int[] newNode = new int[]{changedRow, currCol};
                queue.offer(newNode);
            }
            // Kolla höger
            int changedCol = currCol + 1;
            if (board[currRow][changedCol] == 'x' && !visited[currRow][changedCol]) {
                visited[currRow][changedCol] = true;
                int[] newNode = new int[]{changedRow, currCol};
                queue.offer(newNode);
            }
            // Kolla vänster
            changedCol = currCol - 1;
            if (board[currRow][changedCol] == 'x' && !visited[currRow][changedCol]) {
                visited[currRow][changedCol] = true;
                int[] newNode = new int[]{changedRow, currCol};
                queue.offer(newNode);
            }
        }
        return false;
    }
}

