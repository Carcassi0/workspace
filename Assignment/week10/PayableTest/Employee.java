package week10.PayableTest;


public abstract class Employee implements Payable
{
   private final String firstName;
   private final String lastName;
   private final String socialSecurityNumber;

   public Employee(String firstName, String lastName, 
      String socialSecurityNumber)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.socialSecurityNumber = socialSecurityNumber;
   } 

   // getPaymentAmount() is not overridden

  @Override    
   public String toString()
   {
      return String.format("%s %s%nsocial security number: %s", 
         firstName, lastName, socialSecurityNumber);
   }
}


