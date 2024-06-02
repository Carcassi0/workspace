package week10.EmployeeSort;

import java.util.*;

 // This program demonstrates the use of the Comparable interface.
 
public class EmployeeSortTest
{
   public static void main(String[] args)
   {
      Employee[] staff = new Employee[3];

      staff[0] = new Employee("Carl Cracker", 100000, 1987, 12, 15);
      staff[1] = new Employee("Harry Hacker", 150000, 1989, 10, 1);
      staff[2] = new Employee("Tony Tester", 90000, 1991, 3, 15);

    //   Arrays.sort(staff); // (A)
    // Arrays.sort(staff, new NameComparator());
    Arrays.sort(staff, ((o1, o2) -> o1.getName().compareTo(o2.getName())));

      // print out information about all Employee objects
      for (Employee e : staff)
         System.out.println("name=" + e.getName() + ",salary=" + e.getSalary()+",hire day="+e.getHireDay());
   }
}



