package Assignment.week12.Q2;

public class PairTest3
{
     public static void main(String[] args)
     {
	  Employee e1 = new Employee("Carl Cracker", 75000, 2007, 12, 15);
	  Employee e2 = new Employee("Gus Greedy", 80000, 2000, 1, 15);
	  Pair<Employee> p = new Pair<>(e1,e2);
	  System.out.println("First = "+p.getFirst());
	  System.out.println("Second = "+p.getSecond());
	 
	  Employee e3 = new Employee("Sid Sneaky", 100000, 1989, 1,1);
	 
	  changePartner(p, e3);
	  System.out.println("First = "+p.getFirst());
	  System.out.println("Second = "+p.getSecond()); 

	  Manager m1 = new Manager("Gang Gamchan", 105000, 2007, 12, 15);
	  Manager m2 = new Manager("Yang Manchun", 1280000, 2000, 1, 15);
	  Pair<Manager> p2 = new Pair<>(m1,m2);
	  System.out.println("First = "+p2.getFirst());
	  System.out.println("Second = "+p2.getSecond());

	  Manager m3 = new Manager("Lee Sunshin", 200000, 1989, 1,1);
	  changePartner(p, m3);
	  System.out.println("First = "+p2.getFirst());
	  System.out.println("Second = "+p2.getSecond()); 

     }
   
     public static void changePartner(Pair<Employee> p, Employee e)
     {
   	  p.setSecond(e);
     }
}
