package securitylab;

import java.util.Scanner;

class railfenceCipherHelper {

    int depth;

    String encode(String msg, int depth) throws Exception {
        int r = depth;
        int l = msg.length();
        int c = l / depth;
        int k = 0;
        char mat[][] = new char[r][c];
        String enc = "";
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k != l) {
                    mat[j][i] = msg.charAt(k++);
                } else {
                    mat[j][i] = 'X';
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                enc += mat[i][j];
            }
        }
        return enc;
    }

    String decode(String encmsg, int depth) throws Exception {
        int r = depth;
        int l = encmsg.length();
        int c = l / depth;
        int k = 0;
        char mat[][] = new char[r][c];
        String dec = "";
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = encmsg.charAt(k++);
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                dec += mat[j][i];
            }
        }
        return dec;
    }
}

class Railfencecipher {

    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        railfenceCipherHelper rf = new railfenceCipherHelper();
        String msg, enc, dec;
        System.out.println("Enter the Plain text: ");
        msg = sc.next();
        System.out.println("Enter the depth: ");
        int depth = sc.nextInt();
        enc = rf.encode(msg, depth);
        dec = rf.decode(enc, depth);
        System.out.println("Plain Text:" + msg.toLowerCase());
        System.out.println("Encrypted Message-Cipher Text:" + enc.toUpperCase());
        System.out.printf("Decrypted Message-:" + dec);
    }
}
