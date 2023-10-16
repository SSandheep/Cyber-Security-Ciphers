package securitylab;

import static java.lang.System.out;
import java.util.Scanner;

class RowColumn {

    private String plainText = "";
    private String encryptedText = "";
    private String decryptedText = "";
    private char[][] matrix;
    private int[] key;

    RowColumn(String text, int[] key) {
        plainText = text;
        this.key = key;
        matrix = this.fill();
        this.printMatrix();
        out.println();
        encryptedText = this.encode();
        decryptedText = this.decode();
    }

    private char[][] fill() {
        int col = key.length;
        int row = plainText.length() / col;
        row = (plainText.length() % col == 0) ? row : row + 1;
        char mat[][] = new char[row][col];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (index == plainText.length()) {
                    mat[i][j] = 'X';
                } else {
                    mat[i][j] = plainText.toUpperCase().charAt(index++);
                }
            }
        }
        return mat;
    }

    private void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }
    }

    private String encode() {
        String res = "";
        int count = 0, temp;
        while (count++ < key.length) {
            temp = 0;
            for (int i = 0; i < key.length; i++) {
                if (key[i] == count) {
                    while (temp < matrix.length) {
                        res += matrix[temp++][i];
                    }
                    break;
                }
            }
        }
        return res;
    }

    private String decode() {
        String res = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'X') {
                    break;
                }
                res += matrix[i][j];
            }
        }
        return res;
    }

    public String toString() {
        return "Plain text:" + plainText + "\nEncrypted text:" + encryptedText.toUpperCase() + "\nDecrypted text:" + decryptedText.toLowerCase();
    }
}

public class RowColumnTransposition {

    public static void main(String[] args) {
        String text;
        Scanner sc = new Scanner(System.in);
        int[] key = {1, 3, 2, 5, 4};
        out.println("Enter text:");
        text = sc.nextLine().replaceAll("\\s", "");
        RowColumn rc = new RowColumn(text, key);
        out.println(rc);
    }
}
