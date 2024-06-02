

/**
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class PairTest2
{
   public static void main(String[] args)
   {
      String[] words = { "Mary", "had", "a", "little", "lamb" };
      Pair<String> mm = ArrayAlg.minmax(words);
      System.out.println("min = " + mm.getFirst());
      System.out.println("max = " + mm.getSecond());

      Employee[] emps = new Employee[4];
      emps[0] = new Employee("Kim", 100000.0, 2015, 3, 1);
      emps[1] = new Employee("Lee", 200000.0, 2005, 9, 1);
      emps[2] = new Employee("Hong", 80000.0, 2017, 5, 1);
      emps[3] = new Employee("Park", 150000.0, 2008, 3, 1);
      Pair<Employee> ee = ArrayAlg.minmax(emps);
      System.out.println("min = " + ee.getFirst());
      System.out.println("max = " + ee.getSecond());

}

class ArrayAlg
{

   /**
    * Gets the minimum and maximum of an array of strings.
    * @param a an array of strings
    * @return a pair with the min and max value, or null if a is null or empty
    */

   // public static <T extends Comparable<T>> Pair<T> minmax(T[] a){
   //    if(a == null || a.length == 0) return null;
   //    T min = a[0];
   //    T max = a[0];
   //    for(int i = 1; i<a.length; i++){
   //       if(min.compareTo(a[i])>0) min = a[i];
   //       if(max.compareTo(a[i])<0) max = a[i];
   //    }
   //    return new Pair<>(min, max);
   // }

   public static <T extends Comparable<T>>Pair<T> minmax(T[] a) {
      if (a == null || a.length == 0) return null; T min = a[0];
      T max = a[0];
      for (int i = 1; i < a.length; i++)
      {
      if (min.compareTo(a[i]) > 0) min = a[i]; if (max.compareTo(a[i]) < 0) max = a[i];
      }
      return new Pair<>(min, max); }



   public static Pair<String> minmax(String[] a)
   {
      if (a == null || a.length == 0) return null;
      String min = a[0];
      String max = a[0];
      for (int i = 1; i < a.length; i++)
      {
         if (min.compareTo(a[i]) > 0) min = a[i];
         if (max.compareTo(a[i]) < 0) max = a[i];
      }
      return new Pair<>(min, max);
   }

   public static Pair<Employee> minmax(Employee[] employee)
   {
      if (employee == null || employee.length == 0) return null;

      Employee min = employee[0];
      Employee max = employee[0];

      for (int i = 1; i < employee.length; i++)
      {
         if (min.getSalary() > employee[i].getSalary()) min = employee[i];
         if (max.getSalary() < employee[i].getSalary()) max = employee[i];
      }
      return new Pair<>(min, max);
   }
}
}