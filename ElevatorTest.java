import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

public class ElevatorTest{
    public static void main(String[] args)throws Exception{
        Properties pro = new Properties();
        String url = "C:\\cs245\\MyWork\\";
        pro.load(new FileInputStream(url + "myFile.properties"));
        int duration = Integer.parseInt(pro.getProperty("duration")); // 100
        String structures = pro.getProperty("structures");
        int elevators = Integer.parseInt(pro.getProperty("elevators"));
        int floor = Integer.parseInt(pro.getProperty("floors"));//15 floor elevator
        double passengers = Double.parseDouble(pro.getProperty("passengers"));//0.1
        int elevatorCapacity = Integer.parseInt(pro.getProperty("elevatorCapacity"));//overload people 10
        for(int i = 1; i <= elevators; i++){
            int mFloor;//random which floor of elevator
            if(i == elevators){
                mFloor = getFloor(0, floor, elevators);
            }else{
                mFloor = getFloor(i, floor, elevators);//random current floor of elevator
            }
            int f = getNum(i, floor, mFloor);
            int d = getNum2(i, floor, f);
            //initialize elevator
            if(structures.equals("linked")){
                ElevatorLinked elevator = new ElevatorLinked(i, floor, passengers, elevatorCapacity, duration, mFloor, elevators);
                elevator.setPeople(f, d);
                elevator.isDirectionTop();
                elevator.run();
            }else if(structures.equals("array")){
                ElevatorArray elevator = new ElevatorArray(i, floor, passengers, elevatorCapacity, duration, mFloor, elevators);
                elevator.setPeople(f, d);
                elevator.isDirectionTop();
                elevator.run();
            }
        }

    }

    public static int getFloor(int ids, int floor, int el){
        while(true){
            int num = new Random().nextInt(floor);
            if(num % el == ids && num != 0){
                return num;
            }
        }
    }

    
    //random num is ids' time and current floor no bigger than 5 floors
     
    public static int getNum(int ids, int floor, int f){
        while(true){
            int num = new Random().nextInt(floor);
            if(num % ids == 0 && Math.abs(num - f) <= 5 && num != f && num != 0){
                return num;
            }
        }
    }

    public static int getNum2(int ids, int floor, int f){
        while(true){
            int num = new Random().nextInt(floor);
            if(num % ids == 0 && Math.abs(num - f) <= 5 && num > f && num != 0){
                return num;
            }
        }
    }
}
