package securitylab;

import java.util.Scanner;

public class VigenereCipher {

    static String generateKey(String str, String key) {
        int x = str.length();
        for (int i = 0;; i++) {
            if (x == i) {
                i = 0;
            }
            if (key.length() == str.length()) {
                break;
            }
            key += (key.charAt(i));
        }
        return key;
    }

    static String cipherText(String str, String key) {
        String cipher_text = "";
        for (int i = 0; i < str.length(); i++) {
            int x = (str.charAt(i) + key.charAt(i)) % 26;
            x += 'A';
            cipher_text += (char) (x);
        }
        return cipher_text;
    }

    static String originalText(String cipher_text, String key) {
        String orig_text = "";
        for (int i = 0; i < cipher_text.length()
                && i < key.length(); i++) {
            int x = (cipher_text.charAt(i) - key.charAt(i) + 26) % 26;
            x += 'A';
            orig_text += (char) (x);
        }
        return orig_text;
    }

    static String LowerToUpper(String s) {
        StringBuffer str = new StringBuffer(s);
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
            }
        }
        s = str.toString();
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String:");
        String Str = sc.next();
        System.out.println("Enter Key:");
        String Keyword = sc.next();
        String str = LowerToUpper(Str);
        String keyword = LowerToUpper(Keyword);
        String key = generateKey(str, keyword);
        String cipher_text = cipherText(str, key);
        System.out.println("Ciphertext : " + cipher_text + "\n");
        System.out.println("Original/Decrypted Text : "
                + originalText(cipher_text, key));
    }
}
