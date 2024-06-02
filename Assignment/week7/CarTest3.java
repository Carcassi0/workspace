package week7;

public class CarTest3 {
    public static void main(String[] args) {
        Car[] cars = new Car[3];
        cars[0] = new Car("CA-1111");
        cars[1] = new Bus("NC-2222", 2);
        cars[2] = new Truck("WA-3333", 3);

        for(Car c: cars){
            c.setHour(3);
        }
        for(Car c: cars){
            System.out.println(c);
        }
    }
}
