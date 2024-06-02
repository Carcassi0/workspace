package week10.PayableTest;


public class Loan implements Payable
{
    private String name;
    private double principal;
    private double interest;
    public Loan(String name, double amount)
    {
        this.name = name;
        this.principal= amount;
        interest = 0;
    }
    public void setInterest(double amt)
    {
        interest = amt;
    }
    @Override
    public String toString()
    {
        return String.format("loan: %s%nprincipal:%,.2f%ninterest:%,.2f",
                name, principal, interest);
    }
    public double getPaymentAmount(){
        return principal + interest;
    }
}

