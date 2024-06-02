package Assignment.week5;

public class CarTest1 {
    public static void main(String[] args) {
        Car c = new Car("c-1111", 120);

        Time t = new Time(2024, 3, 20, 21, 30);
        c.setEntering(t);

        Time t2 = new Time(2024, 3, 20, 21, 30);
        t2.addMinute(120);
        c.setExiting(t2);

        c.calcToll();

        System.out.println(c);


    }
}
