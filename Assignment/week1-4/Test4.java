// package Assignment;


// import java.util.Scanner;

// public class Test4 {
//     static Scanner in;

//     public static void main(String[] args) {
//         in = new Scanner(System.in);

//         do{
//             char cmd = read_command();
//             if(cmd == 'e'|| cmd == 'E') {
//                 handle_enter();
//             }
//             else if (cmd == 'l' || cmd == 'L') {
//                 handle_locate();
//             } else if (cmd == 't' || cmd == 'T') {
//                 handle_time();
//             } else {
//                 System.out.printf("command error: %s%n", cmd);
//             }
//         }while(true);
//     }

//     public static char read_command()
//     {
//         System.out.printf("Enter a command: ");
//         String s = in.next();
//         return s.charAt(0);
//     }

//     static void handle_enter(){
//         in.nextLine();
//         System.out.println("handling enter command...");
//     }
//     static void handle_locate(){
//         System.out.println("handling locate command...");
//     }
//     static void handle_time(){
//         System.out.println("handling time command...");
//     }
// }

