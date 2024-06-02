package week8;

public abstract class Vehicle {
    private static final int UNIT = 1000;
    private String name;
    private String no;
    private int hour;
    public Vehicle(String n){
        no = n;
    }
    public String getNo(){return no;}

    public abstract String getName();
    public int getHour(){return hour;}
    public int getUnit(){return UNIT;}
    public void setHour(int h){hour = h;}
    public abstract int calcFee();
    public String toString(){
        return getName() +": "+ calcFee();
    }

}
