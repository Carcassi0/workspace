package week10.PayableTest;


public class SalariedEmployee extends Employee
{
   private double weeklySalary;
   public SalariedEmployee(String firstName, String lastName, 
      String socialSecurityNumber, double weeklySalary)
   {
      super(firstName, lastName, socialSecurityNumber); 
      this.weeklySalary = weeklySalary;
   } 


  @Override                                                         
   public double getPaymentAmount()                                  
   {                                                                 
      return weeklySalary;                                      
   } 
  @Override                                                    
   public String toString()                                     
   {                                                            
      return String.format("salaried employee: %s%n%s: $%,.2f",
         super.toString(), "weekly salary", weeklySalary);
   } 
} // end class SalariedEmployee


