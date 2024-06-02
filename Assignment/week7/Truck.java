package week7;

public class Truck extends Car{
    private static final int UNIT = 1000;
    private int weight;
    public Truck(String no, int w){
        super(no);
        weight = w;
    }
    public int getWeight(){
        return weight;
    }
    public int calcFee(){
        return getHour() * UNIT * weight;
    }
    public String toString(){
        return "Truck: " + calcFee();
    }
}
