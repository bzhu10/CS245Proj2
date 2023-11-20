import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class ElevatorLinked extends Elevator{
    Set<String> linkedSet = new LinkedHashSet<>();//go up

    public ElevatorLinked(int ids, int floor, double passengers, int elevatorCapacity, int duration, int currentFloor, int elevators) {
        super(ids, floor, passengers, elevatorCapacity, duration, currentFloor, elevators);
    }

    
    //people enter elevator and then make sure the taget amount floor
    public void enter(String outFloor, int floor){
        System.out.println(floor + " floor has passengers enter, press " + outFloor + " floor");
        linkedSet.add(outFloor);

        if(linkedSet.size() > 0){
            System.out.println(ids + " number elevator" + " the floor to go now " + linkedSet);
        }
    }

    public void run(){
        for(int tick = 1; tick < duration; tick++){
            try{
                Thread.sleep(2000);//2s 1 floor
                if(directionTop){
                    currentFloor++;
                    System.out.println("\t go up " + ids + " number elevator arrives " + currentFloor + " floor go up");

                    //add beginning passengers
                    if(currentFloor == q){
                        enter(s + "", currentFloor);
                        this.maxFloor = s;
                        q = 0;
                        s = 0;
                    }

                    //this floor passengers go outside and then remove this floor
                    if(linkedSet.contains(currentFloor + "")){
                        System.out.println(ids + " number elevator " + currentFloor + " floor's passengers go outside elevator");
                        linkedSet.remove(currentFloor + "");

                        if(linkedSet.size() == 0 && q == 0){
                            ticks = tick;
                            System.out.println("\t" + ids + " number elevator " + "finish running\t");
                            break;
                        }

                        System.out.println(ids + " number elevator " + "the floor to go now " + linkedSet);
                    }

                    if(q == 0 && currentFloor % elevators == ids || (elevators == ids && currentFloor % elevators == 0)){//only ids' time can wait
                        double enter = new Random().nextInt(10) / 10.0;//identify whether passengers wait for elevator this floor or not
                        if(enter <= passengers){//identify whether passengers enter elevator or not
                            if(linkedSet.size() >= elevatorCapacity){
                                System.out.println(ids + " number elevator" + " has already accepted max, please waiting for next elevator, thanks");
                            }else{
                                int mFloor = getFloor(currentFloor);
                                enter(mFloor + "", currentFloor);
                                if(mFloor > currentFloor && mFloor > this.maxFloor){
                                    this.maxFloor = mFloor;
                                    System.out.println(ids + " number elevator" + " the highest floor passengers to go now: " + maxFloor);
                                }
                                if(mFloor < currentFloor && mFloor < this.minFloor){
                                    this.minFloor = mFloor;
                                }
                            }
                        }
                    }

                    if(maxFloor == currentFloor || currentFloor == floor){
                        directionTop = false;
                        maxFloor = 0;
                        System.out.println(ids + " number elevator" + " begins running down.");
                    }

                }else{
                    currentFloor--;
                    System.out.println("\t go down " + ids + " number elevator arrives " + currentFloor + " floor go down. ");
                    //add beginning passengers
                    if(currentFloor == q){
                        enter(s + "", currentFloor);
                        this.maxFloor = s;
                        this.minFloor = q;
                        q = 0;
                        s = 0;
                    }

                    //this floor passengers go outside elevator and then remove this floor
                    if(linkedSet.contains(currentFloor + "")){
                        System.out.println(ids + " number elevator " + currentFloor + " floor's passengers go outside elevator. ");
                        linkedSet.remove(currentFloor + "");

                        if(linkedSet.size() == 0 && q == 0){
                            ticks = tick;
                            System.out.println("\t" + ids + " number elevator" + " finishes running\t");
                            break;
                        }

                        System.out.println(ids + " number elevator" + " the floor to go now " + linkedSet);
                    }

                    if(q == 0 && currentFloor % elevators == ids || (elevators == ids && currentFloor % elevators == 0)){//only ids' times and then wait
                        double enter = new Random().nextInt(10) / 10.0;
                        if(enter <= passengers){//identify whether passengers enter elevator or not
                            if(linkedSet.size() >= elevatorCapacity){
                                System.out.println(ids + " number elevator" + "has already accepted max, please waiting next elevator, thanks! ");
                            }else{
                                int mFloor = getFloor(currentFloor);
                                enter(mFloor + "", currentFloor);
                                if(mFloor > currentFloor && mFloor > this.maxFloor){
                                    this.maxFloor = mFloor;
                                }
                                if(mFloor < currentFloor && mFloor < this.minFloor){
                                    this.minFloor = mFloor;
                                    System.out.println(ids + " number elevator" + " the lowest floor passengers to go now: " + minFloor);
                                }
                            }
                        }
                    }

                    if(minFloor == currentFloor || 1 == currentFloor){
                        directionTop = true;
                        minFloor = floor;
                        System.out.println(ids + " number elevator" + " begins running up.");
                    }
                }
            } catch (InterruptedException e) {

            }
        }

        System.out.println(ids + " number elevator" + " running time is: " + ticks * duration);
        System.out.println("---------------------------------------");
    }

}
