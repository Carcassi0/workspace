package Assignment.week5;



public class Car {
    private static final int basicToll = 500;
    private static final int distanceRate = 50;
    private String no;
    private int speed;
    private Time entering;
    private Time exiting;
    private int toll;

    public Car(String no, int speed){
        this.no = no;
        this.speed = speed;
    }
    public Car(String no){ // (가)
        this.speed = 100;
        this.entering =null;
        this.exiting = null;
        this.toll = 0;
    }

    public String getNo(){
        return no;
    }
    public int getSpeed(){
        return speed;
    }
    public Time getEntering(){
        return entering;
    }
    public Time getExiting(){
        return exiting;
    }
    public int getToll(){
        return toll;
    }
    public void setEntering(Time t){
        entering = t;
    }
    public void setExiting(Time t){
        exiting =  t;
    }
    public void calcToll(){ // (라)
        int minute = getExiting().timeDifference(getEntering());
        int distance = minute/60 * getSpeed();
        toll = ((basicToll+distance*distanceRate) * getSpeed()/100);

    }
    public String toString(){
        return String.format("%s (%dkm) must pay %d원", no, speed, toll);
    }

}

