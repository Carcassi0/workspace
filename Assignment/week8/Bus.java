package week8;

public class Bus extends Vehicle{
    private int size;
    public Bus(String no, int s){
        super(no);
        size = s;
    }
    public String getName(){
        return "Bus";
    }
    public int getSize(){
        return size;
    }
    public int calcFee(){
        return getHour() * getUnit() * getSize();
    }
}
