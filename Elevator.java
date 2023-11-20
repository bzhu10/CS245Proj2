import java.util.Random;

public class Elevator{
    int ids;//elevator number
    boolean directionTop = true;//elevator runs direction
    int duration;
    int maxFloor;
    int minFloor;
    int currentFloor;//current floor
    int floor;//floor sum
    double passengers;//possibilities passengers enter every floor
    int elevatorCapacity;//elevator max accept
    int elevators;//amount of elevator
    int ticks = 0;//tick
    int q;
    int s;

    public Elevator(int ids, int floor, double passengers, int elevatorCapacity, int duration, int currentFloor, int elevators){
        this.ids = ids;
        this.floor = floor;
        this.passengers = passengers;
        this.elevatorCapacity = elevatorCapacity;
        this.duration = duration;
        this.currentFloor = currentFloor;
        this.minFloor = floor;
        this.elevators = elevators;

        System.out.println("\t " + ids + " number elevator, sum: " + floor + " floor.");
    }

    
    //identify running direction of elevator beginning
     
    public void isDirectionTop(){
        if(currentFloor < q){
            directionTop = true;
            System.out.println("\televator go up beginning");
        }else{
            directionTop = false;
            System.out.println("\televator go down beginning");
        }
    }

    
    //initiallize passengers
    
    public void setPeople(int f, int d){
        this.q = f;
        this.s = d;
        System.out.println("\t " + ids + " number elevator beginning passengers at " + q + " floor, to go " + s + " floor. \t");
        System.out.println("\t current " + ids + " number elevator at " + currentFloor + " floor. \t");
    }

    //random number, ids' time not exceed five floors
    public int getFloor(int f){
        while(true){
            int num = new Random().nextInt(floor);
            if(num % ids == 0 && Math.abs(num - f) <= 5 && num != f && num != 0){
                return num;
            }
        }
    }
}
