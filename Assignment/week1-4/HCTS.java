// package Assignment;

// import java.io.IOException;
// import java.util.*;
// import java.io.*;

// public class HCTS {

//     static Car[] carList;

//     public static void main(String[] args) throws IOException {
//         initialize();
//         for (Car car : carList) {
//             System.out.println(toString(car));
//         }
//     }

//     public static void initialize() throws IOException{

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

//     public static String toString(Car c) {
//         int car_number = c.no;
//         int car_speed = c.speed;
//         double car_position = c.position;
//         String complete_sentence = "car no. = " + car_number + ", car speed. = " + car_speed +  ", car position = " + car_position;
//         return complete_sentence;
//     }
// }

// class  Car{
//     int no;
//     int speed;
//     double position;
// }