package week10.PayableTest;


public class Invoice implements Payable
{
   private final String partNumber; 
   private final String partDescription;
   private int quantity;
   private double pricePerItem;

   public Invoice(String partNumber, String partDescription, int quantity,
      double pricePerItem)
   { 
      this.quantity = quantity;
      this.partNumber = partNumber;
      this.partDescription = partDescription;
      this.pricePerItem = pricePerItem;
   } 

  @Override                                                           
   public double getPaymentAmount()                                    
   {                                                                   
      return quantity * pricePerItem; 
   } 

   public String toString()
   {
      return String.format("invoice: %s %s%nquantity: %d%nprice per item:$%,.2f", 
         partNumber, partDescription, quantity, pricePerItem);
   }
} // end class Invoice

