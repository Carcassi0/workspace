package week7;

public class CarList {
    private Car[] carList;
    private int current = 0;
    public CarList(int n) {
        carList = new Car[n];
    }
    public Car getCar(int i)
    {
        return carList[i];
    }
    public void addCar(Car c)
    {
        for(int i = 0; i< carList.length; i++){
            if(carList[i]==null){
                carList[i] = c;
                break;
            }
        }
    }
    public Car findCar(String no)
    {
        for(int i = 0; i< carList.length; i++) {
            if (carList[i].getNo().equals(no)) {
                return carList[i];
            } 
        }
        return null;
    }
    public int calcTotalFee()
    {
        int totalFee = 0;
        int counter = 0;
        for(int i = 0; i< carList.length; i++){
            if(carList[i]!=null){
                counter += 1;
            }
        }
        for(int i = 0; i<counter; i++){
            totalFee += carList[i].calcFee();
        }
        return totalFee;
    }
}
