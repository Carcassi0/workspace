// Fig. 16.2: CollectionTest.java
// Collection interface demonstrated via an ArrayList object.
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class CollectionTest 
{
   public static void main(String[] args)
   {
      // add elements in colors array to list
      String[] colors = {"MAGENTA", "RED", "WHITE", "BLUE", "CYAN"};
      // List<String> list = new ArrayList<String>();
      List<String> list = new LinkedList<String>();
     

      for (String color : colors)
         list.add(color); // adds color to end of list      

      // add elements in removeColors array to removeList
      String[] removeColors = {"RED", "WHITE", "BLUE"};
      // List<String> removeList = new ArrayList<String>();
      List<String> removeList = new LinkedList<String>();

      for (String color : removeColors)
         removeList.add(color); 

      // output list contents
      System.out.println("ArrayList: ");

      for (int count = 0; count < list.size(); count++)
         System.out.printf("%s ", list.get(count));

      // remove from list the colors contained in removeList
      removeDuplicates(list);

      // output list contents
      System.out.printf("%n%nArrayList after calling removeColors:%n");

      for (String color : list)
         System.out.printf("%s ", color);
   } 
   
   private static <T> List<T> removeDuplicates(List<T> list) 
    {   
        Iterator<T> iter = list.iterator();
        List<T> duplicateList = new LinkedList<>();
        List<T> checked = new LinkedList<>();
        while(iter.hasNext()){
            T str = iter.next();
                if(checked.contains(str)){
                    if(!duplicateList.contains(str)){duplicateList.add(str);}
                    iter.remove();
                } else{
                    checked.add(str);
                }
        }
        return duplicateList;                 
    }
    

   // remove colors specified in collection2 from collection1
   // private static void removeColors(Collection<String> collection1, 
   //    Collection<String> collection2)
   // {
   //    // get iterator
   //    Iterator<String> iterator = collection1.iterator(); 

   //    // loop while collection has items
   //    while (iterator.hasNext())         
   //    {
        
   //       if (collection2.contains(iterator.next()))
   //          iterator.remove(); // remove current element
   //    } 
   // } 
} // end class CollectionTest

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/