// package Assignment;
// import java.io.IOException;
// import java.util.*;
// import java.io.*;

// public class HCTS1
// {
//     static Car[] carList;
//     static Time current_time;
//     static Scanner in;

//     public static void main(String[] args)
//     {
//         initialize();

//         do {
//             char cmd = read_command();
//             if(cmd=='t')
//                 handle_setting_time();
//             else if(cmd=='l')
//                 handle_locating_cars();
//             else
//                 System.out.printf("command error: %s%n", cmd);
//         } while(true);
//     }

//     public static void initialize()
//     {
//         int carQuantity;

//         Scanner in = new Scanner(new File("/home/dnldmlwhd/workspace/Assignment/in.text"), "UTF-8");

//         carQuantity = in.nextInt();

//         carList = new Car[carQuantity];

//         for(int i=0; i<carQuantity; i++){
//             carList[i] = new Car();
//             carList[i].no = in.nextInt();
//             carList[i].speed = in.nextInt();
//             carList[i].position = in.nextInt();
//         }

//     }

//     public static char read_command()
//     {
//         System.out.printf("Enter a command: ");
//         String s = in.next();
//         return s.charAt(0);

//     }

//     public static void handle_setting_time()
//     {
//         Time t = new Time();
//         t.hour = in.nextInt();
//         t.minute = in.nextInt();

//         int min = calculate_time_difference(t);
//         set_current_time(t);
//         move_all_cars(min);

//     }

//     public static void handle_locating_cars()
//     {
//         for(int i=0; i<carList.length; i++)
//             print_car_info(carList[i]);
//     }

//     public static void set_current_time(Time t)
//     {
//         current_time = t;
//     }

//     public static int calculate_time_difference(Time t)
//     {
//         return (t.hour-current_time.hour) * 60 + (t.minute-current_time.minute);

//     }

//     public static void move_all_cars(int min)
//     {
//         for(int i=0; i<carList.length; i++)
//         {
//             carList[i].position += (double) carList[i].speed/60*min;
//             if(carList[i].position>500.0)
//             carList[i].position = 500.0;
//         }
//     }

//     public static void print_car_info(Car c)
//     {
//         System.out.println(toString(c));
//     }

//     static String toString(Car c){
//         int car_number = c.no;
//         double car_position = c.position;
//         String complete_sentence = "car no. = " + car_number + ", car position = " + car_position;
//         return complete_sentence;
//     }
// }

// class Time
// {
//     int hour;
//     int minute;
// }

// class Car
// {
//     int no;
//     int speed;
//     double position;
// }
