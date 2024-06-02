package week10.EmployeeSort;

import java.time.LocalDate;

public class EmployeeTest
{
    public static void main(String[] args)
    {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 100000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 150000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 90000, 1991, 3, 15);
        int n = Employee.howMany(staff, e -> e.getSalary()>100000); // (가)
        int m = Employee.howMany(staff, e -> e.getHireDay().isBefore(LocalDate.of(1990, 1, 1))); // (나)
        System.out.println("Number of High-Salaried Employees = "+n);
        System.out.println("Number of Old Employees = "+m);
    }
}

