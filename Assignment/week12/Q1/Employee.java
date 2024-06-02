
import java.time.*;

public class Employee implements Comparable<Employee>
{  
   private String name;
   private double salary;
   private LocalDate hireDay;

   public Employee(String name, double salary, int year, int month, int day)
   {
      this.name = name;
      this.salary = salary;
      hireDay = LocalDate.of(year, month, day);
   }

   public String getName()
   {
      return name;
   }

   public double getSalary()
   {  
      return salary;
   }

   public LocalDate getHireDay()
   {  
      return hireDay;
   }

   public void raiseSalary(double byPercent)
   {  
      double raise = salary * byPercent / 100;
      salary += raise;
   }

   public String toString() 
   {
        return "["+name+","+salary+","+hireDay+"]";
   }

   public int compareTo(Employee other)
   {
        if(this.salary-other.salary < 0) return -1;
        else if(this.salary-other.salary > 0) return 2;
        else return 0;
   }


}

