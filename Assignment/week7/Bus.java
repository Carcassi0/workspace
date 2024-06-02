package week7;

public class Bus extends Car{
    private static final int UNIT = 1000;
    private int size;
    public Bus(String no, int s){
        super(no);
        size = s;
    }
    public int getSize(){return size;}
    public String getName(){
        return "Bus";
    }
    public int calcFee(){
        return getHour() * UNIT * size;
    }
}
