package week10.EmployeeSort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class Employee implements Comparable<Employee>
{
   private final String name;
   private double salary;
   private final LocalDate hireDay;

   public Employee(String name, double salary, int year, int month, int day) {
      this.name = name;
      this.salary = salary;
      hireDay = LocalDate.of(year, month, day);
   }
   public String getName() {
      return name;
   }
   public double getSalary() {
      return salary;
   }
   public LocalDate getHireDay() 
  { 
     return hireDay;
   }
   public void raiseSalary(double byPercent) {
      double raise = salary * byPercent / 100;
      salary += raise;
   }
    public int compareTo(Employee other) {  // Compare employees by salary
        return Double.compare(salary, other.salary);
    }

    // public int compareTo(Employee other){
    //    return name.compareTo(other.name);
    // }
    public static int howMany(Employee[] emps, P t) {
      int n = 0;
      for (Employee e : emps)
          if (t.test(e)) n++;
          return n;
  }

}

class NameComparator implements Comparator<Employee>{
    public int compare(Employee other){
        return other.getName().compareTo(other.getName());
    }

    @Override
    public int compare(Employee o1, Employee o2) {
        return 0;
    }
}
