// package Assignment;

// import java.util.Scanner;

// public class Test1 {
//     public static void main(String[] args) {

//         Scanner in = new Scanner(System.in);

//         Time t1 = new Time();
//         t1.hour = in.nextInt();
//         t1.miniute = in.nextInt();

//         Time t2 = new Time();
//         t2.hour = 2; t2.miniute = 30;

//         System.out.println("t1 hour = "+t1.hour);
//         System.out.println("t1 minute = "+t1.miniute);
//         System.out.println("time difference = "+calculate_time_difference(t1, t2));

//     }
//     static int calculate_time_difference(Time t1, Time t2){
//         int t1_miniute = (t1.hour * 60) + t1.miniute;
//         int t2_miniute = (t2.hour * 60) + t2.miniute;

//         int time_difference =  t1_miniute - t2_miniute;

//         return time_difference;
//     }
// }

// class Time{
//     int hour;
//     int miniute;
// }
