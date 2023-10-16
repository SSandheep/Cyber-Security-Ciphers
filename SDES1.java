package securitylab;
import static java.lang.System.out;
class SDES{
 private String plainText="";
 private String key="";
 private String cipherText="";
 private String decryptedText="";
 private String key1="";
 private String key2="";
 private final int[] P10={3,5,2,7,4,10,1,9,8,6};
 private final int[] P8={6,3,7,4,8,5,10,9};
 private final int[] P4={2,3,4,1};
 private final int[] EP={4,1,2,3,2,3,4,1};
 private final int[] IP={2,6,3,1,4,8,5,7};
 private final int[] IP_1={4,1,3,5,7,2,8,6};
 private final int[][] S0={
 {1,0,3,2},
 {3,2,1,0},
 {0,2,1,3},
 {3,1,0,2}
 };
 private final int[][] S1={
 {0,1,2,3},
 {2,0,1,3},
 {3,0,1,0},
 {2,1,0,3}
 };
 SDES(String text,String key){
 plainText=text;
 this.key=key;
 this.generateKey();
 cipherText=this.encrypt();
 decryptedText=this.decrypt();
 }
 private String initialPermute(String text){
 String res="";
 for(int i=0;i<text.length();i++)
 res+=text.charAt(IP[i]-1);
 return res;
 }
 private String encrypt(){
 String text=this.initialPermute(plainText);
 String res="";
 String left="";
 String right="";
 for(int i=0;i<2;i++){
 if(i==0){
 left=text.substring(0,4);
 right=text.substring(4,8);
 for(int j=0;j<EP.length;j++)
 res+=right.charAt(EP[j]-1);
 res=this.XOR(res, key1);
 String a=this.SBox(S0, res.substring(0,4))+this.SBox(S1, res.substring(4,8));
 right="";
 for(int k=0;k<P4.length;k++)
 right+=a.charAt(P4[k]-1);
 a=this.XOR(left, right);
 text=text.substring(4,8)+a;
 }else{
 res="";
 left=text.substring(0,4);
 right=text.substring(4,8);
 for(int j=0;j<EP.length;j++)
 res+=right.charAt(EP[j]-1);
 res=this.XOR(res, key2);
 String a=this.SBox(S0, res.substring(0,4))+this.SBox(S1, res.substring(4,8));
 right="";
 for(int k=0;k<P4.length;k++)
 right+=a.charAt(P4[k]-1);
 a=this.XOR(left, right);
 text=a+text.substring(4,8);
 }
 }
 res="";
 for(int i=0;i<IP_1.length;i++)
 res+=text.charAt(IP_1[i]-1);
 return res;
 }
 private String decrypt(){
 String text=this.initialPermute(cipherText);
 String res="";
 String left="";
 String right="";
 for(int i=0;i<2;i++){
 if(i==0){
 left=text.substring(0,4);
 right=text.substring(4,8);
 for(int j=0;j<EP.length;j++)
 res+=right.charAt(EP[j]-1);
 res=this.XOR(res, key2);
 String a=this.SBox(S0, res.substring(0,4))+this.SBox(S1, res.substring(4,8));
 right="";
 for(int k=0;k<P4.length;k++)
 right+=a.charAt(P4[k]-1);
 a=this.XOR(left, right);
 text=text.substring(4,8)+a;
 }else{
 res="";
 left=text.substring(0,4);
 right=text.substring(4,8);
 for(int j=0;j<EP.length;j++)
 res+=right.charAt(EP[j]-1);
 res=this.XOR(res, key1);
 String a=this.SBox(S0, res.substring(0,4))+this.SBox(S1, res.substring(4,8));
 right="";
 for(int k=0;k<P4.length;k++)
 right+=a.charAt(P4[k]-1);
 a=this.XOR(left, right);
 text=a+text.substring(4,8);
 }
 }
 res="";
 for(int i=0;i<IP_1.length;i++)
 res+=text.charAt(IP_1[i]-1);
 return res;
 }
 private int getNum(String c){
 int n=0;
 int a=c.charAt(0)-'0';
 int b=c.charAt(1)-'0';
 if(a==0 && b==0)
 n=0;
 else if(a==0 && b==1)
 n=1;
 else if(a==1 && b==0)
 n=2;
 else
 n=3;
 return n;
 }
 private String SBox(int[][] Box,String a){
 String res="";
 int row=this.getNum(""+a.charAt(0)+a.charAt(3));
 int col=this.getNum(""+a.charAt(1)+a.charAt(2));
 int num=Box[row][col];
 if(num==0)
 res+="00";
 else if(num==1)
 res+="01";
 else if(num==2)
 res+="10";
 else
 res+="11";
 return res;
 }
 private String XOR(String a,String b){
 String res="";
 for(int i=0;i<a.length();i++)
 if(a.charAt(i)==b.charAt(i))
 res+='0';
 else
 res+='1';
 return res;
 }
 private void generateKey(){
 for(int i=0;i<P10.length;i++)
 key1+=key.charAt(P10[i]-1);
 String a=key1.substring(1,5)+key1.charAt(0);
 String b=key1.substring(6,key1.length())+key1.charAt(5);
 key1="";
 for(int i=0;i<P8.length;i++)
 key1+=(a+b).charAt(P8[i]-1);
 a=a.substring(2,5)+a.substring(0,2);
 b=b.substring(2,5)+b.substring(0,2);
 for(int i=0;i<P8.length;i++)
 key2+=(a+b).charAt(P8[i]-1);
 
 }
 @Override
 public String toString(){
 return "Plain text:"+plainText+"\nEncrypted text:"+cipherText+"\nDecrypted text:"+decryptedText;
 }
 
}
public class SDES1{
 public static void main(String[] args) {
 SDES a=new SDES("10100101","0010010111");
 out.println(a);
 }
}
