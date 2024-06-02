package Assignment.week11;
import java.io.*;
import java.util.Scanner; 

class FileInputTest { 
   public static FileInputStream foo(String fileName) throws FileNotFoundException
   {
     System.out.println("foo: Started");
     FileInputStream fis = new FileInputStream(fileName);
     System.out.println("foo: Returned");
     return fis;
   } 

  public static void main(String args[])  
   {
      try {
        FileInputStream fis = null;
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();

      System.out.println("main: Started"); 

      fis = foo(fileName);

      System.out.println("main: Ended");
        
      } catch (Exception e) {
        System.out.println("파일이 존재하지 않는다");
        return;
      }
      
   }
 } 