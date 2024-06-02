package Assignment1;
import java.io.IOException;
import java.util.Scanner;

import Assignment2.Car;
import Assignment2.Time;

public class HCMSbackup {
    static Car[] carList;
    static Time initialized_time;
    static Time current_time;
    static int basicCharge;
    static int distanceCharge;
    static double timeDifference;
    static Scanner scanner;

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
                viewAllVehiclesOnHighway();
            } else if (cmd == 'x') {
                viewAllVehiclesExitingHighway();
            } else if (cmd == 'q') {
                quit();
            } else if (cmd == 'r'){
                registeringANewCar();
            } else
                System.out.printf("잘못된 명령어입니다.: %s%n", cmd);
        } while (true);
    }

    public static void initialize() {
        scanner = new Scanner(System.in);
        int carQuantity;
        basicCharge = scanner.nextInt();
        distanceCharge = scanner.nextInt();
        scanner.nextLine();
        carQuantity = scanner.nextInt();
        scanner.nextLine();
        carList = new Car[carQuantity];
        for (int i = 0; i < carQuantity; i++) {
            carList[i] = new Car();
            carList[i].no = scanner.nextInt();
            carList[i].cc = scanner.nextInt();
            carList[i].speed = scanner.nextInt();
            carList[i].position = 0;
        }
        initialized_time.year = 2024;
        initialized_time.month = 3;
        initialized_time.day = 20;
        initialized_time.hour = 21;
        initialized_time.minute = 0;

        current_time.setTime(initialized_time.year, initialized_time.month, initialized_time.day, initialized_time.hour, initialized_time.minute);
    }
    public static void setCurrentTime() {
        //test
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

        timeDifference = calculate_time_difference(current_time, initialized_time); // 두 번 이상 실행할 때 거리 계산이 이상함.

        for (Car car : carList) {
            if (car.entered && car.selected && !car.out) {
                double applyPosition = timeDifference * car.speed;
                car.positionGap -= applyPosition;
                if(car.isReverse){
                    car.fromSeoulDistance = (int) (car.endPosition + car.positionGap);
                }else{
                    car.fromSeoulDistance = (int) (car.endPosition - car.positionGap);
                }
            }
        }

        for (Car car : carList) {
            if (car.fromSeoulDistance >= car.endPosition && car.entered && car.selected) { // if (car.positionGap <= 0 && car.entered && car.selected)
                car.selected = false;
                car.entered = false;
                car.out = true;

                double toll = (basicCharge + car.toll_distance * distanceCharge) * ((double)car.speed / 100) * ((double)car.cc / 2000);
                int truncatedToll = (int) toll; // 실수값을 정수로 변환
                truncatedToll = truncatedToll - (truncatedToll % 10);
                car.toll = truncatedToll;
            }
        }
    }

    public static double calculate_time_difference(Time currentTime, Time initializedTime) {

        boolean isLeapYearA = isLeapYear(currentTime.year);
        boolean isLeapYearB = isLeapYear(initializedTime.year);

        int[] daysInMonthCurrent = {31, isLeapYearA ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] daysInMonthInitialized = {31, isLeapYearB ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        double currentTotalMinutes = yearToHours(currentTime.year) * 60 + monthToHoursCurrent(currentTime.month, daysInMonthCurrent) * 60 +
                currentTime.day * 24 * 60 + currentTime.hour * 60 + currentTime.minute;
        double initializedTotalMinutes = yearToHours(initializedTime.year) * 60 + monthToHoursInitialized(initializedTime.month, daysInMonthInitialized) * 60 +
                initializedTime.day * 24 * 60 + initializedTime.hour * 60 + initializedTime.minute;

        double minuteDifference = Math.abs(currentTotalMinutes - initializedTotalMinutes);
        double hourDifference = minuteDifference / 60;

        return hourDifference; // 1시간 단위 아래로 변하면 소수점 아래의 정보가 모두 0으로 표기됨
    }

    public static double calculate_time_differenceNoABS(Time currentTime, Time initializedTime) {

        boolean isLeapYearA = isLeapYear(currentTime.year);
        boolean isLeapYearB = isLeapYear(initializedTime.year);

        int[] daysInMonthCurrent = {31, isLeapYearA ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] daysInMonthInitialized = {31, isLeapYearB ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        double currentTotalMinutes = yearToHours(currentTime.year) * 60 + monthToHoursCurrent(currentTime.month, daysInMonthCurrent) * 60 +
                currentTime.day * 24 * 60 + currentTime.hour * 60 + currentTime.minute;
        double initializedTotalMinutes = yearToHours(initializedTime.year) * 60 + monthToHoursInitialized(initializedTime.month, daysInMonthInitialized) * 60 +
                initializedTime.day * 24 * 60 + initializedTime.hour * 60 + initializedTime.minute;

        double minuteDifference = currentTotalMinutes - initializedTotalMinutes;
        double hourDifference = minuteDifference / 60;

        return hourDifference; // 1시간 단위 아래로 변하면 소수점 아래의 정보가 모두 0으로 표기됨
    }
    public static void enter() {
        int selectedCarNo = scanner.nextInt();
        String startPoint = scanner.next();
        String endPoint = scanner.next();

        Car selectedCar = null;
        for (Car car : carList) {
            if (car.no == selectedCarNo) {
                selectedCar = car;
                break;
            }
        }
        if (selectedCar == null) {
            System.out.println("존재하지 않는 차량입니다.");
            return;
        }
        if (selectedCar.entered) {
            System.out.println("이미 고속도로 상에 진입한 차량입니다.");
            return;
        }
        if (!(startPoint.equals("서울") || startPoint.equals("수원") || startPoint.equals("대전") || startPoint.equals("대구") || startPoint.equals("부산"))
                || !(endPoint.equals("서울") || endPoint.equals("수원") || endPoint.equals("대전") || endPoint.equals("대구") || endPoint.equals("부산"))) {
            System.out.println("진입 및 진출 지점은 서울, 수원, 대전, 대구, 부산 중 하나여야 합니다.");
            return;
        }
        selectedCar.selected = true;
        selectedCar.entered = true;
        selectedCar.startPoint = startPoint;
        selectedCar.endPoint = endPoint;
        selectedCar.enteredTime.setTime(current_time.year, current_time.month, current_time.day, current_time.hour, current_time.minute);
        //

        for (Car car : carList) {
            if (car.no == selectedCarNo) {
                if(!car.selected){
                    car.selected = true;
                    car.entered = true;
                    car.startPoint = startPoint;
                    car.endPoint = endPoint;
                    car.enteredTime.setTime(current_time.year, current_time.month, current_time.day, current_time.hour, current_time.minute);
                    car.startPosition = 0;
                    car.endPosition = 0;
                    break;
                }
            }
        }

        for(Car car: carList){
            if(car.no ==selectedCarNo){
                switch (car.startPoint) {
                    case "서울":
                        car.startPosition = 0;
                        break;
                    case "수원":
                        car.startPosition = 30;
                        break;
                    case "대전":
                        car.startPosition = 130;
                        break;
                    case "대구":
                        car.startPosition = 290;
                        break;
                    case "부산":
                        car.startPosition = 400;
                        break;
                }

                switch (car.endPoint) {
                    case "서울":
                        car.endPosition = 0;
                        break;
                    case "수원":
                        car.endPosition = 30;
                        break;
                    case "대전":
                        car.endPosition = 130;
                        break;
                    case "대구":
                        car.endPosition = 290;
                        break;
                    case "부산":
                        car.endPosition = 400;
                        break;
                }
            }
        }

        for(Car car: carList){ // 문제 없음
            if(car.no == selectedCarNo){
                if (car.endPosition < car.startPosition) {
                    car.positionGap = car.startPosition - car.endPosition;
                    car.toll_distance = car.startPosition - car.endPosition;
                    car.isReverse = true;
                } else {
                    car.positionGap = car.endPosition - car.startPosition;
                    car.toll_distance = car.endPosition - car.startPosition;
                }
            }
        }
    }

    public static void viewAllVehiclesOnHighway() {
        int rowNum = 0;
        boolean onHighway = false;
        for (Car car : carList) {
            if (car.entered) {
                onHighway = true;
                if ("서울".equals(car.startPoint)) {
                    ++rowNum;
                    System.out.printf("%d. %d %dkm // 서울에서 %dkm 지점%n",
                            rowNum, car.no, car.fromSeoulDistance, car.fromSeoulDistance);
                }
            }
            }
        if(!onHighway){
            System.out.printf("%02d/%02d/%02d-%02d:%02d 에 통행 차량이 없습니다!%n",
                    current_time.year, current_time.month,
                    current_time.day, current_time.hour, current_time.minute);
        }
    }

    public static void viewAllVehiclesExitingHighway() {
        // out time 계산
        for (Car car : carList) {
            if (!sameTime(current_time, car.enteredTime)){
                if (car.out) {
                    Time temp = new Time(); // temp 객체 생성

                    // 시간 계산
                    int gapHour = (int) car.toll_distance / car.speed;
                    int gapMinute = ((int) car.toll_distance * 60 / car.speed) % 60; // 서울 대전 기준 130 % 100
                    int gapDay = gapHour / 24;
                    gapHour = gapHour % 24;
                    int gapMonth = 0, gapYear = 0;

                    // temp에 car.enteredTime 복사
                    temp.year = car.enteredTime.year;
                    temp.month = car.enteredTime.month;
                    temp.day = car.enteredTime.day;
                    temp.hour = car.enteredTime.hour;
                    temp.minute = car.enteredTime.minute;

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
                    // car.outTime에 temp 할당
                    car.outTime = temp;
                }
            }
        }


        int rowNum = 0;
        boolean hasOutCars = false;

        for (Car car : carList) {
            if (car.out) {
                ++rowNum;
                System.out.printf("%d. %d %02d/%02d/%02d-%02d:%02d %d원%n",
                        rowNum, car.no,
                        car.outTime.year, car.outTime.month, car.outTime.day,
                        car.outTime.hour, car.outTime.minute,
                        car.toll);
                hasOutCars = true;
            }
        }
        if (!hasOutCars) {
            System.out.println("진출한 차량이 없습니다!");
        }
    }

    public static void registeringANewCar(){
        int carNumber = scanner.nextInt();
        int cc = scanner.nextInt();
        int speed = scanner.nextInt();
        Car newcar = new Car();

        newcar.no = carNumber;
        newcar.cc = cc;
        newcar.speed = speed;

        Car[] temp = new Car[carList.length+1];
        for(int i = 0; i< carList.length; i++){
            if(newcar.no == carList[i].no){
                System.out.println("이미 등록된 차량입니다");
                return;
            }
            temp[i] = carList[i];
        }
        temp[carList.length] = newcar;
        carList = temp;

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

    public static int yearToHours(int year) {
        return year * 365 * 24 + leapYearCount(year) * 24;
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

    public static int leapYearCount(int year) {
        int count = 0;
        for (int i = 1; i < year; i++) {
            if (isLeapYear(i)) {
                count++;
            }
        }
        return count;
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
        if (currentTime.month < 1 || currentTime.month > 12 || currentTime.day < 1 || currentTime.hour < 0 || currentTime.hour > 23 || currentTime.minute < 0 || currentTime.minute > 59){
            System.out.println("잘못된 시간을 입력하셨습니다");
            return false;
        }
        int[] daysInMonth = {31, (isLeapYear(currentTime.year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (currentTime.day > daysInMonth[currentTime.month - 1]) {
            System.out.println("존재하지 않는 날짜입니다.");
            return false;
        }
        if(calculate_time_differenceNoABS(currentTime, initializedTime)<0){
            System.out.println("입력한 시간이 기준시간보다 과거입니다.");
            return false;
        }
        if(calculate_time_differenceNoABS(currentTime, initializedTime)==0){
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


class Car {
    int no;
    int cc;
    int speed;
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
}
