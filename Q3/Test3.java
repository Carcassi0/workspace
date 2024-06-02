import java.util.*;

public class Test3
{
     public static void main(String[] args)
     {
          ArrayList<Employee> le = new ArrayList<>();
	      le.add(new Employee("Carl Cracker", 75000, 2007, 12, 15));
	      le.add(new Employee("Gus Greedy", 80000, 2000, 1, 15));
	      le.add(new Employee("Sid Sneaky", 100000, 1989, 1,1));
          
          printEmployee(le);

          ArrayList<Manager> lm = new ArrayList<>();
	      lm.add(new Manager("Gang Gamchan", 75000, 2007, 12, 15));
	      lm.add(new Manager("Yang Manchun", 80000, 2000, 1, 15));
	      lm.add(new Manager("Lee Sunshin", 100000, 1989, 1,1));
	
          printEmployee(lm);
     }
       

     public static void printEmployee(ArrayList<? extends Employee> le)
     {
     	 for(Employee e : le)
              System.out.println(e);
     }

}
