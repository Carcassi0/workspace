package week8;

public class Car extends Vehicle {
    private int cc;

    public Car(String n, int displacement) {
        super(n);
        cc = displacement;
    }
    public String getName(){
        return "Car";
    }
    public int calcFee(){
        if(cc <= 1000){
            return getHour() * getUnit();
        }
        else if (cc>1000 && cc<=2000) {
            return getHour() * getUnit() * 2;
        }
        else{
            return getHour() * getUnit() * 3;
        }
    }
}

