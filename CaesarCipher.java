package securitylab;
import java.util.*;

public class CaesarCipher 
{
         
        private static String encryption(String text)
        {
        String str="";
        for(int i=0;i<text.length();i++)
            str+=(char)((text.charAt(i)-97+3)%26+97);
        return str.toUpperCase();
        }
    private static String decryption(String text){
        String str="";
        for(int i=0;i<text.length();i++)
            str+=(char)((text.charAt(i)-65-3)%26+65);
        return str.toLowerCase();
        }
    public static void main(String[] args) 
    {
        String text;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text:");
        text=sc.next();
        System.out.println("Plain text:"+text.toLowerCase());
        String cipherText=encryption(text);
        System.out.println("Encrypted text:"+cipherText);
        String plainText=decryption(cipherText);
        System.out.println("Decrypted text:"+plainText);
    }
        
 }
    


