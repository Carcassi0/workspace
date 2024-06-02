package week7;

public class CarTest1 {
    public static void main(String[] args) {
        Car c = new Car("CA-1111");
        c.setHour(5);
        System.out.println(c.toString());
        Bus b = new Bus("NC-2222", 2);
        // Car b = new Bus("NC-2222", 2);
        b.setHour(2);
        System.out.println(b.toString());
    }
}
