package securitylab;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
public class RSA {
    public static int encrypt(int M,int e,int n)
    {
        int C=(int)pow(M,e);
        int CT=C%n;
        return CT;
    }
     public static BigInteger decrypt(long CT,int d,int n)
    {
        BigInteger bigc=BigInteger.valueOf(CT);
        BigInteger PT,N;
        N=BigInteger.valueOf(n);
        PT = bigc.pow(d).mod(N);
        return PT;
    }
     public static boolean isPrime(int Num)
     {
         int flag=0;
         for(int i=2;i<sqrt(Num);i++ )
         {
             if(Num%i==0)
                 flag=1;
         }
         if(flag==0)
             return true;
         else
             return false;
         
     }
     public static int gcd(int n1,int n2)
     {
         int gcd=1;
         int small;
         if(n1>n2)
            small=n2;
         else
            small=n1;
         for(int i=1;i<=small;i++)
         {
             if(n1%i==0 && n2%i==0)
                 gcd=i;
         }
         return gcd;
     }

    public static void main(String[] args)
    {
        System.out.println("Enter the Message:");
        Scanner sc=new Scanner(System.in);
        int message=sc.nextInt();    
        System.out.println("Enter the primes p and q:");
        int p=sc.nextInt();
        int q=sc.nextInt();
        if(isPrime(p) && isPrime(q) && p!=q)
        {
         int n=p*q;
         int pin=(p-1)*(q-1);
         int e=2;
         while(e<pin)
         {
           
             if(gcd(e,pin)==1)
                 break;
             else
                 e++;
             
         }
            int d=2;
            while(d<pin)
            {
                if((e*d)%pin==1)
                    break;
                else
                   d++;
            }

            System.out.println("e:"+e);
            System.out.println("d:"+d);
            int PublicKey[]={e,n};
            int PrivateKey[]={d,n};
            int CipherText=encrypt(message,e,n);
            BigInteger PlainText=decrypt(CipherText,d,n);
         
       
            System.out.println("Public key is:"+Arrays.toString(PublicKey));
            System.out.println("Private key is:"+Arrays.toString(PrivateKey));
            System.out.println("Plain Text is:"+PlainText);
            System.out.println("CipherText is:"+CipherText);
         
       
       }
     
    }  
}
