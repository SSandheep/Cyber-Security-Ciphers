package securitylab;
import java.util.*;

public class HillCipher {
    
public static int[][] keymat = new int[][]
{
{ 1, 2, 1 },
{ 2, 3, 2 },
{ 2, 2, 1 },
};
public static int[][] invkeymat = new int[][]
{
{ -1, 0, 1 },
{ 2, -1, 0 },
{ -2, 2, -1},
};
public static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
public static void main(String[] args)
{
String text,outtext ="",outtext1="";
int ch, n;
Scanner sc=new Scanner(System.in);
System.out.println("Enter the Plain text for Encryption: ");
text=sc.next();
text = text.toUpperCase();
text = text.replaceAll("\\s","");
n = text.length() % 3;
if(n!=0)
{
for(int i = 1; i<= (3-n);i++)
{
text+= 'X';
}
}
System.out.println("Padded Text:" + text);
char[] ptextchars = text.toCharArray();
for(int i=0;i< text.length(); i+=3)
{
outtext += encrypt(ptextchars[i],ptextchars[i+1],ptextchars[i+2]);
}
System.out.println("Encypted Message: " + outtext);
char[] ptextchars1 = outtext.toCharArray();
for(int i=0;i< outtext.length(); i+=3)
{
outtext1 += decrypt(ptextchars1[i],ptextchars1[i+1],ptextchars1[i+2]);
}
System.out.println("Decrypted Message: " + outtext1);
}
private static String encrypt(char a, char b, char c)
{
String ret = "";
int x,y, z;
int posa = (int)a - 65;
int posb = (int)b - 65;
int posc = (int)c - 65;
x = posa * keymat[0][0] + posb * keymat[1][0] + posc * keymat[2][0];
y = posa * keymat[0][1] + posb * keymat[1][1] + posc * keymat[2][1];
z = posa * keymat[0][2] + posb * keymat[1][2] + posc * keymat[2][2];
a = key.charAt(x%26);
b = key.charAt(y%26);
c = key.charAt(z%26);
ret = "" + a + b + c;
return ret;
}
private static String decrypt(char a, char b, char c)
{
String ret = "";
int x,y,z;
int posa = (int)a - 65;
int posb = (int)b - 65;
int posc = (int)c - 65;
x = posa * invkeymat[0][0]+ posb * invkeymat[1][0] + posc * invkeymat[2][0];
y= posa * invkeymat[0][1]+ posb * invkeymat[1][1] + posc * invkeymat[2][1];
z = posa * invkeymat[0][2]+ posb * invkeymat[1][2] + posc * invkeymat[2][2];
a = key.charAt((x%26<0)?(26+x%26):(x%26));
b = key.charAt((y%26<0)?(26+y%26):(y%26));
c = key.charAt((z%26<0)?(26+z%26):(z%26));
ret = "" + a + b + c;
return ret;
}
}
    

