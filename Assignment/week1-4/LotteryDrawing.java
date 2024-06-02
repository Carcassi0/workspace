// package Assignment;

// import java.util.*;

// /**
//  * This program demonstrates array manipulation.
//  * @version 1.20 2004-02-10
//  * @author Cay Horstmann
//  */
// public class LotteryDrawing
// {
//     static int[] result;
//     static int r;

//     public static void main(String[] args)
//     {
//         Scanner in = new Scanner(System.in);
//         System.out.print("How many numbers do you need to draw? ");
//         int k = in.nextInt();
//         System.out.print("What is the highest number you can draw? ");
//         int n = in.nextInt();

//         int[] numbers = new int[n];
//         int[] result = new int[k];
//         // initialize
//         initailize(numbers); // done
//         // draw
//         draw(numbers, result);
//         // print
//         print(result);
//     }

//     public static void initailize(int[] numbers){
//         for (int i = 0; i < numbers.length; i++)
//             numbers[i] = i + 1;
//     }

//     public static void draw(int[] numbers, int[] result){
//         int l = numbers.length;
//         for (int i = 0; i < result.length; i++)
//         {
//             r = (int) (Math.random() * l);

//             result[i] = numbers[r];

//             numbers[r] = numbers[l - 1];
//             l--;
//         }
//     }

//     public static void print(int[] result){
//         Arrays.sort(result);
//         System.out.println("Bet the following combination. It'll make you rich!");
//         for (int r : result)
//             System.out.println(r);
//     }
// }