package week8;

public class Truck extends Vehicle {
    private int weight;
    public Truck(String no, int w){
        super(no);
        weight = w;
    }
    public int getWeight(){
        return weight;
    }
    public String getName(){
        return "Truck";
    }
    public int calcFee(){
        return getHour() * getUnit() * getWeight();
    }
}

