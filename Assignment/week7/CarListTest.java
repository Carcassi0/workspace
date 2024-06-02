package week7;

public class CarListTest {
    public static void main(String[] args)
    {
        CarList cl = new CarList(10);
        Car c = new Car("CA-1111"); 
        c.setHour(5);
        cl.addCar(c);
        Bus b = new Bus("NC-2222", 2);
        b.setHour(2);
        cl.addCar(b);
        Truck t = new Truck("WA-3333", 2); 
        t.setHour(2);
        cl.addCar(t);
        System.out.println("Total fee: "+cl.calcTotalFee());
        c = cl.findCar("NC-2222");
        System.out.println(c);
    }
}
