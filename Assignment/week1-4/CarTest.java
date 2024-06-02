package Assignment;

import Assignment1.Car;

public class CarTest
{
    public static void main(String[] args)
    {
        Car[] carList = new Car[2];

        carList[0] = new Car("c-1111", 2500, 120);
        carList[1] = new Car("c-2222", 2000, 100);

        for(Car c: carList){
            int speed = c.getSpeed();
            int after30m = speed/2;
            c.setPosition(after30m);
        }
            // (바)

        for(Car c : carList)
            System.out.println(c.toString());   // (A)

        for(Car c: carList){
            double speedUp = c.getSpeed() * 0.1;
            c.addSpeed((int)speedUp);
        }
            // (사)

        for(Car c: carList)
            System.out.printf("School_Assignment.Car %s(%dcc, %dkm) located at %dkm%n", c.getNo(),
                    c.getDisplacement(), c.getSpeed(), c.getPosition());

        if(carList[0].compareTo(carList[1])>0)					// (B)
            System.out.printf("%s is greater than  %s%n", carList[0].getNo(), carList[1].getNo());
        else if(carList[0].compareTo(carList[1]) < 0)
            System.out.printf("%s is greater than  %s%n", carList[1].getNo(), carList[0].getNo());
        else
            System.out.printf("%s equals to %s%n", carList[1].getNo(), carList[0].getNo());
    }
}
class Car
{
    private String no;
    private int speed;
    private int displacement;
    private int position;

    public Car(String no, int displacement, int speed)
    {
        this.no = no;
        this.displacement = displacement;
        this.speed = speed;
        this.position = 0;
    }

    public String getNo() { return no; }
    public int getDisplacement() { return displacement; }
    public int getSpeed() { return speed; }
    public int getPosition() { return position; }

    public void setPosition(int pos)
    {
        if(pos>=0){
            position += pos;
        }
    }

    public void addSpeed(int amt)
    {
        if(speed < 0){
            speed = 0;
        } else if (speed > 200) {
            speed = 200;
        } else{
            speed += amt;
        }

    }
    public void addSpeed()
    {
        if(speed < 0){
            speed = 0;
        } else if (speed > 200) {
            speed = 200;
        } else{
            speed += 10;
        }
    }

    public String toString() 					// (라)
    {
        String car_number = no;
        int car_displacement = displacement;
        int car_speed = speed;
        int car_position = position;

        String complete_sentence = "School_Assignment.Car "+car_number+"("+car_displacement+"cc, "+car_speed+"km) located at "+car_position+"km";
        return  complete_sentence;
    }

    public int compareTo(Car c) 				// (마)
    {
        if(this.speed > c.speed){
            return 1;
        } else if (this.speed == c.speed) {
            return 0;
        } else {
            return -1;
        }
    }

}

