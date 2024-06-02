import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class hcms3_202022040 {
    static Vehicle[] VehicleList;
    static Time initialized_time;
    static Time current_time;
    static double timeDifference;
    static Scanner scanner;
    static tollCalculator tollCalculator;
    static timeCalculator timeCalculator;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        initialized_time = new Time();
        current_time = new Time();
        initialize();

        do{
            String sentence = scanner.next();
            char cmd = sentence.charAt(0);
            if (cmd == 't')
                setCurrentTime();
            else if (cmd == 'n')
                enter();
            else if (cmd == 'o') {
                viewAllVehicleOnHighway();
            } else if (cmd == 'x') {
                viewAllVehicleExitingHighway();
            } else if (cmd == 'q') {
                quit();
            } else if (cmd == 'r'){
                registeringAnewVehicle();
            } else
                System.out.printf("잘못된 명령어입니다.: %s%n", cmd);
        } while (true);
    }

    public static void initialize() throws FileNotFoundException {
        Scanner in = new Scanner(new File("hcms.txt"));
        int VehicleQuantity;
        int basicCharge;
        int distanceCharge;

        basicCharge = in.nextInt();
        distanceCharge = in.nextInt();
        in.nextLine();
        VehicleQuantity = in.nextInt();
        in.nextLine();
        VehicleList = new Vehicle[VehicleQuantity]; 
        tollCalculator = new tollCalculator();
        tollCalculator.calculatorSetting(basicCharge, distanceCharge);

        for (int i = 0; i < VehicleQuantity; i++) {
            
            switch(in.next()){
                case "g":
                Vehicle gascar = new gasCar();
                gascar.type = 1;
                gascar.no = in.nextInt();
                gascar.speed = in.nextInt();
                gascar.cc = in.nextInt();
                VehicleList[i] = gascar;
                break;

                case "h":
                Vehicle hybridcar = new hybridCar();
                hybridcar.type = 2;
                hybridcar.no = in.nextInt();
                hybridcar.speed = in.nextInt();
                hybridcar.cc = in.nextInt();
                VehicleList[i] = hybridcar;
                break;

                case "b":
                Vehicle bus = new Bus();
                bus.type = 3;
                bus.no = in.nextInt();
                bus.speed = in.nextInt();
                bus.maxPassengers = in.nextInt();
                VehicleList[i] = bus;
                break;

                case "t":
                Vehicle truck = new Truck();
                truck.type = 4;
                truck.no = in.nextInt();
                truck.speed = in.nextInt();
                truck.ton = in.nextInt();
                VehicleList[i] = truck;
                break;
            }            
            VehicleList[i].position = 0;
        }

        initialized_time.year = 2024;
        initialized_time.month = 3;
        initialized_time.day = 20;
        initialized_time.hour = 21;
        initialized_time.minute = 0;

        current_time.setTime(initialized_time.year, initialized_time.month, initialized_time.day, initialized_time.hour, initialized_time.minute);
    }
    public static void setCurrentTime() {
        timeCalculator = new timeCalculator();
        initialized_time.setTime(current_time.year, current_time.month, current_time.day, current_time.hour, current_time.minute);
        try {
            current_time.year = scanner.nextInt();
            current_time.month = scanner.nextInt();
            current_time.day = scanner.nextInt();
            current_time.hour = scanner.nextInt();
            current_time.minute = scanner.nextInt();
        } catch (Exception e){
            System.out.println("잘못된 입력입니다");
            return;
        }

        if (!isValidDate(current_time, initialized_time)) {
            return;
        }

        timeDifference = timeCalculator.calculateTimeDifference(current_time, initialized_time, false);

        for (Vehicle Vehicle : VehicleList) {
            if (Vehicle.entered && Vehicle.selected && !Vehicle.out) {
                double applyPosition = timeDifference * Vehicle.speed;
                Vehicle.positionGap -= applyPosition;
                if(Vehicle.isReverse){
                    Vehicle.fromSeoulDistance = (int) (Vehicle.endPosition + Vehicle.positionGap);
                }else{
                    Vehicle.fromSeoulDistance = (int) (Vehicle.endPosition - Vehicle.positionGap);
                }
            }
        }

        for (Vehicle Vehicle : VehicleList) {
            Vehicle.out = false; // 이전 시간 설정에서 진출한 차량 삭제
            if(Vehicle.isReverse){
                if ((-1)*Vehicle.fromSeoulDistance >= Vehicle.endPosition && Vehicle.entered && Vehicle.selected) { // if (Vehicle.positionGap <= 0 && Vehicle.entered && Vehicle.selected)
                    Vehicle.selected = false;
                    Vehicle.entered = false;
                    Vehicle.out = true;
                    Vehicle.toll = tollCalculator.calculateToll(Vehicle.toll_distance, Vehicle.extraCharge());
                    Vehicle.fromSeoulDistance = 0; // 이미 진출한 차량 다시 계산하는 것 방지하기 위해 추가
                }
            }
            if(!Vehicle.isReverse){
                if (Vehicle.fromSeoulDistance >= Vehicle.endPosition && Vehicle.entered && Vehicle.selected) { // if (Vehicle.positionGap <= 0 && Vehicle.entered && Vehicle.selected)
                    Vehicle.selected = false;
                    Vehicle.entered = false;
                    Vehicle.out = true;
                    Vehicle.toll = tollCalculator.calculateToll(Vehicle.toll_distance, Vehicle.extraCharge());
                    Vehicle.fromSeoulDistance = 0; // 이미 진출한 차량 다시 계산하는 것 방지하기 위해 추가
                }
            }
        }
    }
    public static void enter() {
        int selectedVehicleNo = scanner.nextInt();
        String startPoint = scanner.next();
        String endPoint = scanner.next();

        Vehicle selectedVehicle = null;
        for (Vehicle Vehicle : VehicleList) {
            if (Vehicle.no == selectedVehicleNo) {
                selectedVehicle = Vehicle;
                break;
            }
        }
        if (selectedVehicle == null) {
            System.out.println("존재하지 않는 차량입니다.");
            return;
        }
        if (selectedVehicle.entered) {
            System.out.println("이미 고속도로 상에 진입한 차량입니다.");
            return;
        }
        if (!(startPoint.equals("서울") || startPoint.equals("수원") || startPoint.equals("대전") || startPoint.equals("대구") || startPoint.equals("부산"))
                || !(endPoint.equals("서울") || endPoint.equals("수원") || endPoint.equals("대전") || endPoint.equals("대구") || endPoint.equals("부산"))) {
            System.out.println("진입 및 진출 지점은 서울, 수원, 대전, 대구, 부산 중 하나여야 합니다.");
            return;
        }
        selectedVehicle.selected = true;
        selectedVehicle.entered = true;
        selectedVehicle.startPoint = startPoint;
        selectedVehicle.endPoint = endPoint;
        selectedVehicle.enteredTime.setTime(current_time.year, current_time.month, current_time.day, current_time.hour, current_time.minute);
        //

        for (Vehicle Vehicle : VehicleList) {
            if (Vehicle.no == selectedVehicleNo) {
                if(!Vehicle.selected){
                    Vehicle.selected = true;
                    Vehicle.entered = true;
                    Vehicle.startPoint = startPoint;
                    Vehicle.endPoint = endPoint;
                    Vehicle.enteredTime.setTime(current_time.year, current_time.month, current_time.day, current_time.hour, current_time.minute);
                    Vehicle.startPosition = 0;
                    Vehicle.endPosition = 0;
                    break;
                }
            }
        }

        for(Vehicle Vehicle: VehicleList){
            if(Vehicle.no ==selectedVehicleNo){
                switch (Vehicle.startPoint) {
                    case "서울":
                        Vehicle.startPosition = 0;
                        break;
                    case "수원":
                        Vehicle.startPosition = 30;
                        break;
                    case "대전":
                        Vehicle.startPosition = 130;
                        break;
                    case "대구":
                        Vehicle.startPosition = 290;
                        break;
                    case "부산":
                        Vehicle.startPosition = 400;
                        break;
                }

                switch (Vehicle.endPoint) {
                    case "서울":
                        Vehicle.endPosition = 0;
                        break;
                    case "수원":
                        Vehicle.endPosition = 30;
                        break;
                    case "대전":
                        Vehicle.endPosition = 130;
                        break;
                    case "대구":
                        Vehicle.endPosition = 290;
                        break;
                    case "부산":
                        Vehicle.endPosition = 400;
                        break;
                }
            }
        }

        for(Vehicle Vehicle: VehicleList){ 
            if(Vehicle.no == selectedVehicleNo){
                if (Vehicle.endPosition < Vehicle.startPosition) {
                    Vehicle.positionGap = Vehicle.startPosition - Vehicle.endPosition;
                    Vehicle.toll_distance = Vehicle.startPosition - Vehicle.endPosition;
                    Vehicle.fromSeoulDistance = (int) (Vehicle.endPosition + Vehicle.positionGap);
                    Vehicle.isReverse = true;
                } else {
                    Vehicle.positionGap = Vehicle.endPosition - Vehicle.startPosition;
                    Vehicle.toll_distance = Vehicle.endPosition - Vehicle.startPosition;
                    Vehicle.fromSeoulDistance = (int) (Vehicle.endPosition - Vehicle.positionGap);
                }
            }
        }
    }

    public static void viewAllVehicleOnHighway() {
        int rowNum = 0;
        boolean onHighway = false;
        for (Vehicle Vehicle : VehicleList) {
            if (Vehicle.entered) {
                onHighway = true;
                ++rowNum;
                System.out.printf("%d. %s %d %dkm%n",
                        rowNum, Vehicle.name(), Vehicle.no, Vehicle.fromSeoulDistance);

            }
        }
        if(!onHighway){
            System.out.printf("통행 차량이 없습니다!%n");
        }
    }
    
    

    public static void viewAllVehicleExitingHighway() {
        // out time 계산
        for (Vehicle Vehicle : VehicleList) {
            if (!sameTime(current_time, Vehicle.enteredTime)){
                if (Vehicle.out) {
                    Time temp = new Time(); // temp 객체 생성

                    // 시간 계산
                    int gapHour = (int) Vehicle.toll_distance / Vehicle.speed;
                    int gapMinute = ((int) Vehicle.toll_distance * 60 / Vehicle.speed) % 60; // 서울 대전 기준 130 % 100
                    int gapDay = gapHour / 24;
                    gapHour = gapHour % 24;
                    int gapMonth = 0, gapYear = 0;

                    // temp에 Vehicle.enteredTime 복사
                    temp.year = Vehicle.enteredTime.year;
                    temp.month = Vehicle.enteredTime.month;
                    temp.day = Vehicle.enteredTime.day;
                    temp.hour = Vehicle.enteredTime.hour;
                    temp.minute = Vehicle.enteredTime.minute;

                    // 시간 간격 더하기
                    temp.minute += gapMinute;
                    if (temp.minute >= 60) {
                        temp.minute -= 60;
                        temp.hour += 1;
                    }
                    temp.hour += gapHour;
                    if (temp.hour >= 24) {
                        temp.hour -= 24;
                        temp.day += 1;
                    }
                    temp.day += gapDay;
                    while (temp.day > daysInMonth(temp.year, temp.month)) {
                        temp.day -= daysInMonth(temp.year, temp.month);
                        temp.month += 1;
                        if (temp.month > 12) {
                            temp.month -= 12;
                            temp.year += 1;
                        }
                    }
                    // Vehicle.outTime에 temp 할당
                    Vehicle.outTime = temp;
                }
            }
        }


        int rowNum = 0;
        boolean hasOutVehicles = false;

        for (Vehicle Vehicle : VehicleList) {
            if (Vehicle.out) {
                ++rowNum;
                System.out.printf("%d. %s %d %02d/%02d/%02d-%02d:%02d %d원%n",
                        rowNum, Vehicle.name(), Vehicle.no,
                        Vehicle.outTime.year, Vehicle.outTime.month, Vehicle.outTime.day,
                        Vehicle.outTime.hour, Vehicle.outTime.minute,
                        Vehicle.toll);
                hasOutVehicles = true;
            }
        }
        if (!hasOutVehicles) {
            System.out.println("진출한 차량이 없습니다!");
        }
    }

    public static void registeringAnewVehicle(){
        Vehicle newVehicle = null;
        switch(scanner.next()){
            case "g":
            gasCar gascar = new gasCar();
            gascar.type = 1;
            gascar.no = scanner.nextInt();
            gascar.speed = scanner.nextInt();
            gascar.cc = scanner.nextInt();
            newVehicle = gascar;
            break;

            case "h":
            hybridCar hybridcar = new hybridCar();
            hybridcar.type = 2;
            hybridcar.no = scanner.nextInt();
            hybridcar.speed = scanner.nextInt();
            hybridcar.cc = scanner.nextInt();
            newVehicle = hybridcar;
            break;

            case "b":
            Bus bus = new Bus();
            bus.type = 3;
            bus.no = scanner.nextInt();
            bus.speed = scanner.nextInt();
            bus.maxPassengers = scanner.nextInt();
            newVehicle = bus;
            break;

            case "t":
            Truck truck = new Truck();
            truck.type = 4;
            truck.no = scanner.nextInt();
            truck.speed = scanner.nextInt();
            truck.ton = scanner.nextInt();
            newVehicle = truck;
            break;
        }

        Vehicle[] temp = new Vehicle[VehicleList.length+1];
        for(int i = 0; i< VehicleList.length; i++){
            if(newVehicle.no == VehicleList[i].no){
                System.out.println("이미 등록된 차량입니다");
                return;
            }
            temp[i] = VehicleList[i];
        }
        temp[VehicleList.length] = newVehicle;
        VehicleList = temp;

    }
    public static int daysInMonth(int year, int month) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) // 윤년인 경우
                    return 29;
                else // 윤년이 아닌 경우
                    return 28;
            default:
                return -1; // 잘못된 월 입력
        }
    }

    public static void quit() {
        System.exit(1);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static boolean sameTime(Time current_time, Time entered_time){
        if(
                current_time.year == entered_time.year&&
                        current_time.month==entered_time.month&&
                        current_time.day==entered_time.day&&
                        current_time.hour==entered_time.hour&&
                        current_time.minute==entered_time.minute
        ){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isValidDate(Time currentTime, Time initializedTime) {
        timeCalculator = new timeCalculator();
        if (currentTime.month < 1 || currentTime.month > 12 || currentTime.day < 1 || currentTime.hour < 0 || currentTime.hour > 23 || currentTime.minute < 0 || currentTime.minute > 59){
            System.out.println("잘못된 시간을 입력하셨습니다");
            return false;
        }
        int[] daysInMonth = {31, (isLeapYear(currentTime.year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (currentTime.day > daysInMonth[currentTime.month - 1]) {
            System.out.println("존재하지 않는 날짜입니다.");
            return false;
        }
        if(timeCalculator.calculateTimeDifference(currentTime, initializedTime, false) <0){
            System.out.println("입력한 시간이 기준시간보다 과거입니다.");
            return false;
        }
        if(timeCalculator.calculateTimeDifference(currentTime, initializedTime, true)==0){
            System.out.println("기준시간과 입력한 시간이 같습니다.");
            return false;
        }
        return true;
    }
}

class Time {
    int year;
    int month;
    int day;
    int hour;
    int minute;

    public void setTime(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
}


abstract class Vehicle {
    int no;
    int cc;
    int speed;
    int type;
    String startPoint;
    String endPoint;
    double position;
    boolean entered = false;
    boolean out = false;
    boolean isReverse = false;
    boolean selected = false; // 진출시 true에서 false로 바꾸어야함
    final Time enteredTime = new Time();
    Time outTime = new Time();
    double positionGap = 0; // 진출지점까지 남은 거리를 저장하는 변수
    double toll_distance = 0; // 진입지점과 진출지점 사이의 거리를 저장, 처음 입력되고 수정 x
    int toll = 0;
    double startPosition = 0;
    double endPosition = 0;
    int fromSeoulDistance = 0;
    int maxPassengers = 0;
    int ton = 0;
    int displacement = 0;

    public String name(){
        switch (this.type) {
            case 1: return "가솔린차"; 
            case 2: return "하이브리드차";
            case 3: return "버스";
            case 4: return "트럭";
            default: return "알 수 없는 차량";
        }
    }
    public abstract double extraCharge();
}

class Bus extends Vehicle{
    public double extraCharge(){
        double passengersCharge = 0;
        if(this.maxPassengers < 12){passengersCharge = 0.8;}
        if(this.maxPassengers >= 12 && this.maxPassengers < 20){passengersCharge = 1.0;}
        if(this.maxPassengers >= 20 && this.maxPassengers < 30){passengersCharge = 1.2;}
        if(this.maxPassengers >= 30){passengersCharge = 1.5;}
        return (((double)this.speed/100.0) * passengersCharge);
    }
}

class Truck extends Vehicle{
    public double extraCharge(){
        double tonCharge = 0;
        if(this.ton < 1){tonCharge = 0.8;}
        if(this.ton >= 1 && this.ton < 2){tonCharge = 1.0;}
        if(this.ton >= 2 && this.ton < 4){tonCharge = 1.2;}
        if(this.ton >= 4){tonCharge = 1.5;}
        return (((double)this.speed/100.0) * tonCharge);
    }
}

class Car extends Vehicle{
    public double extraCharge(){return 0;}
    
}

class gasCar extends Car{
    public double extraCharge(){
        double ccCharge = 0;
        if(this.cc < 1000){ccCharge = 0.8;}
        if(this.cc >= 1000 && this.cc < 2000){ccCharge = 1.0;}
        if(this.cc >= 2000 && this.cc < 3000){ccCharge = 1.2;}
        if(this.cc >= 3000){ccCharge = 1.5;}
        return (((double)this.speed/100.0) * ccCharge);
    }
}

class hybridCar extends Car{
    public double extraCharge(){
        double ccCharge = 0;
        if(this.cc < 1000){ccCharge = 0.8;}
        if(this.cc >= 1000 && this.cc < 2000){ccCharge = 1.0;}
        if(this.cc >= 2000 && this.cc < 3000){ccCharge = 1.2;}
        if(this.cc >= 3000){ccCharge = 1.5;}
        return (((double)this.speed/200.0) * ccCharge);
    }
}

class tollCalculator{
    private int basicCharge = 0;
    private int distanceCharge = 0;
    private int toll = 0;

    public void calculatorSetting(int basicCharge, int distanceCharge){
        this.basicCharge = basicCharge;
        this.distanceCharge = distanceCharge;
    }
    public int calculateToll(double distance, double extraCharge){
        double tempToll = 0.0;
        tempToll = (this.basicCharge + distance * this.distanceCharge) * extraCharge;
        toll = (int)tempToll;
        toll = toll - (toll%10);
        return toll;
    }
}

class timeCalculator{
    public static double calculateTimeDifference(Time currentTime, Time initializedTime, boolean absolute) {
        boolean isLeapYearA = isLeapYear(currentTime.year);
        boolean isLeapYearB = isLeapYear(initializedTime.year);

        int[] daysInMonthCurrent = {31, isLeapYearA ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] daysInMonthInitialized = {31, isLeapYearB ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        double currentTotalMinutes = yearToHours(currentTime.year) * 60 + monthToHoursCurrent(currentTime.month, daysInMonthCurrent) * 60 +
                currentTime.day * 24 * 60 + currentTime.hour * 60 + currentTime.minute;
        double initializedTotalMinutes = yearToHours(initializedTime.year) * 60 + monthToHoursInitialized(initializedTime.month, daysInMonthInitialized) * 60 +
                initializedTime.day * 24 * 60 + initializedTime.hour * 60 + initializedTime.minute;

        double minuteDifference = absolute ? Math.abs(currentTotalMinutes - initializedTotalMinutes) : currentTotalMinutes - initializedTotalMinutes;
        double hourDifference = minuteDifference / 60;

        return hourDifference;
    }
    public static int yearToHours(int year) {
        return year * 365 * 24 + leapYearCount(year) * 24;
    }
    public static int leapYearCount(int year) {
        int count = 0;
        for (int i = 1; i < year; i++) {
            if (isLeapYear(i)) {
                count++;
            }
        }
        return count;
    }
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static int monthToHoursCurrent(int month, int[] daysInMonth) {
        int totalHours = 0;
        for (int i = 1; i < month; i++) {
            totalHours += daysInMonth[i - 1] * 24;
        }
        return totalHours;
    }
    public static int monthToHoursInitialized(int month, int[] daysInMonth) {
        int totalHours = 0;
        for (int i = 1; i < month; i++) {
            totalHours += daysInMonth[i - 1] * 24;
        }
        return totalHours;
    }
}