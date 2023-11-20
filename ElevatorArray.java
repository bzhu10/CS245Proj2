import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElevatorArray extends Elevator{
    List<String> linkedSet = new ArrayList<>();

    public ElevatorArray(int ids, int floor, double passengers, int elevatorCapacity, int duration, int currentFloor, int elevators){
        super(ids, floor, passengers, elevatorCapacity, duration, currentFloor, elevators);
    }

    
    //choose target floor when passengers enter
    public void enter(String outFloor, int floor){
        System.out.println(floor + " floor has passengers enter，print go " + outFloor + " floor.");
        if(!isRepeat(linkedSet, outFloor)){
            linkedSet.add(outFloor);
        }

        if(linkedSet.size() > 0){
            System.out.println(ids + " number elevator" + " the floor now to go " + linkedSet);
        }
    }

    public boolean isRepeat(List<String> list, String name){
        for(String s : list){
            if(s.equals(name))
                return true;
        }
        return false;
    }

    public void run(){
        for(int tick = 1; tick < duration; tick++){
            try{
                Thread.sleep(2000);//2s one floor
                if(directionTop){
                    currentFloor++;
                    System.out.println("\t go up " + ids + " number elevator arrive " + currentFloor + " floor up");

                    //add beginning passengers
                    if(currentFloor == q){
                        enter(s + "", currentFloor);

                        this.maxFloor = s;

                        q = 0;
                        s = 0;
                    }

                    //this floor elevator passengers' go outside and then remove this floor
                    if(linkedSet.contains(currentFloor + "")){
                        System.out.println(ids + " number elevator " + currentFloor + " floor's passengers go outside elevator.");
                        linkedSet.remove(currentFloor + "");

                        if (linkedSet.size() == 0 && q == 0) {
                            ticks = tick;
                            System.out.println("\t" + ids + " number elevator" + " finish running\t");
                            break;
                        }

                        System.out.println(ids + " number elevator" + " the floor currently goes " + linkedSet);
                    }

                    if(q == 0 && currentFloor % elevators == ids || (elevators == ids && currentFloor % elevators == 0)){//only ids'time and wait
                        double enter = new Random().nextInt(10) / 10.0;//identify this floor whether passengers wait elevator or not

                        if(enter <= passengers) {//whether passengers enter or not
                            if(linkedSet.size() >= elevatorCapacity){
                                System.out.println(ids + " number elevator " + "max accept aleady, please waiting next elevator, thanks! ");
                            }else{
                                int mFloor = getFloor(currentFloor);
                                enter(mFloor + "", currentFloor);
                                if(mFloor > currentFloor && mFloor > this.maxFloor){
                                    this.maxFloor = mFloor;
                                    System.out.println(ids + " number elevator " + "the highest floor passengers to go now: " + maxFloor);
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
                        System.out.println(ids + " number elevator " + "begins going down");
                    }

                } else {
                    currentFloor--;
                    System.out.println("\t go down " + ids + " number elevator arrive " + currentFloor + " floor goes down. ");

                    //add beginning passengers
                    if(currentFloor == q){
                        enter(s + "", currentFloor);

                        this.maxFloor = s;

                        this.minFloor = q;

                        q = 0;
                        s = 0;
                    }

                    //this floor's passengers go outside and then remove this floor
                    if(linkedSet.contains(currentFloor + "")){
                        System.out.println(ids + " number elevator " + currentFloor + " floor's passengers go outside elevator");
                        linkedSet.remove(currentFloor + "");

                        if(linkedSet.size() == 0 && q == 0){
                            ticks = tick;
                            System.out.println("\t" + ids + " number elevator " + "finish running\t");
                            break;
                        }

                        System.out.println(ids + " number elevator" + " the floor to go now " + linkedSet);
                    }

                    if(q == 0 && currentFloor % elevators == ids || (elevators == ids && currentFloor % elevators == 0)){//only ids' time can wait
                        double enter = new Random().nextInt(10) / 10.0;
                        if(enter <= passengers){//identify whether passengers enter elevator or not
                            if(linkedSet.size() >= elevatorCapacity){
                                System.out.println(ids + " number elevator" + " has already accepted max，please waiting next elevator, thanks! ");
                            }else{
                                int mFloor = getFloor(currentFloor);
                                enter(mFloor + "", currentFloor);
                                if(mFloor > currentFloor && mFloor > this.maxFloor){
                                    this.maxFloor = mFloor;
                                }
                                if(mFloor < currentFloor && mFloor < this.minFloor){
                                    this.minFloor = mFloor;
                                    System.out.println(ids + " number elevator " + "the lowest floor passengers to go now " + minFloor);
                                }
                            }
                        }
                    }

                    if(minFloor == currentFloor || 1 == currentFloor){
                        directionTop = true;
                        minFloor = floor;
                        System.out.println(ids + " number elevator" + " begins going up. ");
                    }
                }
            }catch(InterruptedException e){

            }
        }

        System.out.println(ids + " number elevator" + " running tims is: " + ticks * duration);
        System.out.println("---------------------------------------");
    }
}
