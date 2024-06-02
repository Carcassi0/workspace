// package Assignment;

// import java.util.*;

// public class Stack
// {
//     private static final int MAX = 10;
//     private static int top=0;
//     private static int[] s = new int[MAX];
//     public static int pop()
//     {
//         if (top == 0)  {
//             System.out.println("Empty!");
//             System.exit(-1); // 강제종료
//         } else      top--;
//         return s[top];
//     }
//     public static void push(int x)
//     {
//         if(top==MAX) {
//             System.out.println("Full!");
//             System.exit(-1);
//         } else  {
//             s[top] = x;
//             top++;
//         }
//         return;
//     }
//     public static void main(String[] args)
//     {
//         Scanner in = new Scanner(System.in);
//         int a = in.nextInt();
//         int b = in.nextInt();
//         String symbol = in.next();

//         Stack.push(a);
//         Stack.push(b);

//         int pop_b = Stack.pop();
//         int pop_a = Stack.pop();

//         int result;
//         switch (symbol){
//             case "+":
//                 result = pop_a+pop_b;
//                 System.out.println(result);
//                 break;
//             case "-":
//                 result = pop_a-pop_b;
//                 System.out.println(result);
//                 break;
//             case "*":
//                 result = pop_a*pop_b;
//                 System.out.println(result);
//                 break;
//             case "/":
//                 result = pop_a/pop_b;
//                 System.out.println(result);
//                 break;
//             default:
//                 System.out.println("Error");
//                 System.exit(-1);

//         }


//     }
// } // end of Stack