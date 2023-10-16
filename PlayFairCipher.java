package securitylab;

import java.util.Scanner;

class PlayFair { 
    private String text;
    private String encryptedText="";
    private String decryptedText="";
    private String key;
    char[][] table;
    PlayFair(String key,String text){
        this.key=key.toUpperCase();
        this.text=text.toUpperCase();
        table=this.fillTable();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++)
                System.out.print(table[i][j]+" ");
            System.out.println();
        }
        encryptedText=this.encrypt();
        decryptedText=this.decrypt();
    }
    private char[][] fillTable(){
        char[][] matrix=new char[5][5];
        boolean[] used=new boolean[26];
        int row=0,col=0;
        for(int i=0;i<26;i++)
            used[i]=false;
        for(int i=0;i<key.length();i++){
            char ch=key.charAt(i);
            if(ch=='J')
                ch='I';
            if(!used[ch-65]){
                used[ch-65]=true;
                matrix[row][col]=ch;
                col++;
                if(col==5){
                    row++;
                    col=0;
                }
            }
        }
            for(int i=0;i<26;i++){
                char ch=(char)(i+65);
                if(ch=='J')
                    continue;
                if(!used[i]){
                    used[i]=true;
                    matrix[row][col]=ch;
                    col++;
                    if(col==5){
                        row++;
                        col=0;
                }
            }
        }
        return matrix;
    }
    private String encrypt(){
        String result="";
        encryptedText=this.preprocess();
        int[] pos1;
        int[] pos2;
        for(int i=0;i<encryptedText.length();i+=2){
            pos1=this.findPosition(encryptedText.charAt(i));
            pos2=this.findPosition(encryptedText.charAt(i+1));
            if(pos1[0]==pos2[0]){
                int newCol1=(pos1[1]+1)%5;
                int newCol2=(pos2[1]+1)%5;
                result+=table[pos1[0]][newCol1];
                result+=table[pos2[0]][newCol2];
            }else if(pos1[1]==pos2[1]){
                int newRow1=(pos1[0]+1)%5;
                int newRow2=(pos2[0]+1)%5;
                result+=table[newRow1][pos1[1]];
                result+=table[newRow2][pos2[1]];
            }else{
                result+=table[pos1[0]][pos2[1]];
                result+=table[pos2[0]][pos1[1]];
            }
        }
        return result;
    }
    private String decrypt(){
        String result="";
        int[] pos1;
        int[] pos2;
        for(int i=0;i<encryptedText.length();i+=2){
            pos1=this.findPosition(encryptedText.charAt(i));
            pos2=this.findPosition(encryptedText.charAt(i+1));
            if(pos1[0]==pos2[0]){
                int newCol1=(pos1[1]-1+5)%5;
                int newCol2=(pos2[1]-1+5)%5;
                result+=table[pos1[0]][newCol1];
                result+=table[pos2[0]][newCol2];
            }else if(pos1[1]==pos2[1]){
                int newRow1=(pos1[0]-1+5)%5;
                int newRow2=(pos2[0]-1+5)%5;
                result+=table[newRow1][pos1[1]];
                result+=table[newRow2][pos2[1]];
            }else{
                result+=table[pos1[0]][pos2[1]];
                result+=table[pos2[0]][pos1[1]];
            }
        }
        decryptedText=result;
        return this.postprocess();
    }
    private int[] findPosition(char ch){
        int[] pos=new int[2];
        for(int i=0;i<5;i++)
            for(int j=0;j<5;j++)
                if(table[i][j]==ch){
                    pos[0]=i;
                    pos[1]=j;
                    return pos;
                }
         return null;        
    }
    private String preprocess(){
        StringBuilder sb=new StringBuilder(text.replaceAll("[^A-Z]", ""));
        for(int i=1;i<sb.length();i+=2)
            if(sb.charAt(i)==sb.charAt(i-1))
                sb.insert(i,'X');
        if(sb.length()%2!=0)
            sb.append('X');
               
        return sb.toString();
    }
    private String postprocess(){
        StringBuilder sb=new StringBuilder(decryptedText);
        for(int i=1;i<sb.length();i+=2){
            if(sb.charAt(i)=='X')
                sb.deleteCharAt(i);
        }
        if(sb.charAt(sb.length()-1)=='X')
            sb.deleteCharAt(sb.length()-1);
        for(int i=0;i<text.length();i++)
            if(!Character.isLetter(text.charAt(i)))
                sb.insert(i, ' ');
        return sb.toString();
    }
    @Override
    public String toString(){
        return "Plain text:"+text.toLowerCase()+"\nEncrypted text:"+encryptedText+"\nDecrypted text:"+decryptedText.toLowerCase();
    }
}
public class PlayFairCipher {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text:");
        String text=sc.nextLine();
        System.out.print("Enter key:");
        String key=sc.next();
        PlayFair pf=new PlayFair(key,text);
        System.out.println(pf);
    }
    
}
