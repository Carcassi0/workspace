// package Assignment;

// import java.util.*;
// import java.io.*;

// public class Test5 {
//     public static void main(String[] args) throws FileNotFoundException {


//         String cmd;
//         int param1;
//         int param2;

//         Scanner in = new Scanner(new File("/home/dnldmlwhd/workspace/Assignment/in.text"), "UTF-8");

//         while(in.hasNext()){
//             cmd = in.next();
//             if(cmd.equals("enter")){
//                 param1 = in.nextInt();
//                 System.out.println("e"+" "+ param1);
//             } else if (cmd.equals("locate")) {
//                 System.out.println("l");
//             } else if (cmd.equals("time")) {
//                 param1 = in.nextInt();
//                 param2 = in.nextInt();
//                 System.out.println("t" + " " + param1 +" "+ param2);
//             } else{
//                 System.out.println("Command error");
//             }
//         }
//     }
// }

