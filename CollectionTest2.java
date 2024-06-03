 // CollectionTest2.java
 import java.util.*;

 public class CollectionTest2  
 {
    public static void main(String[] args)
    {
      // add elements in colors array to list
      String[] colors = {"MAGENTA", "RED", "WHITE", "BLUE", "CYAN", "RED", "CYAN", "RED", "BLUE"};
      LinkedList<String> list = new LinkedList<String>();      

      makeList(list, colors); //2-

      // output list contents
      System.out.printf("%n%nOriginal colors:%n");
      printList(list);

      //***
      // get duplicates
      List<String> list2 = removeDuplicates(list); // 2-

      // output list contents
      System.out.printf("%n%nDuplicate-removed colors:%n");
      printList(list);

      // output list contents
      System.out.printf("%n%nDuplicated colors:%n");
      printList(list2);
    } 

    private static void printList(List<String> list)
    {
      for (String color : list)
         System.out.printf("%s ", color);
    }

    private static void makeList(LinkedList<String> list, String[] colors)        // 2-가
    {
        ListIterator<String> iter = list.listIterator();
        for(String color:colors){
            iter.add(color);
        }
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

    
 }