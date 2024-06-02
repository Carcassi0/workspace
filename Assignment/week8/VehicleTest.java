package week8;

public class VehicleTest
{
    public static void main(String[] args)
    {
        Vehicle[] vList = new Vehicle[4];
        vList[0] = new Car("CA-1111", 2000);
        vList[0].setHour(3);
        vList[1] = new Bus("NC-2222", 2);
        vList[1].setHour(3);
        vList[2] = new Truck("WA-3333", 1);
        vList[2].setHour(3);
        vList[3] = new Car("CO-4444", 1500);
        vList[3].setHour(4);

        for(Vehicle v : vList)
            System.out.println(v.toString()); // System.out.println(v);
    }
}


