package week7;

public class Car {
    private static int UNIT = 1000;
    private String no;
    private int hour;

    public Car(String n){no = n;}
    public String getNo(){return no;}
    public int getHour(){return hour;}
    public void setHour(int h){hour = h;}
    public int calcFee(){return hour * UNIT;}
    public String getName(){
        return "Car";
    }
    public String toString(){
        return getName()+": "+ calcFee();
    }
}

