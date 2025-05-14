public class Elevator {
    
    public static void main ( String[] args ) {
        
        int numFloors = Integer.parseInt(args[0]); //read the number of floors
        int floorRequests = Integer.parseInt(args[1]); //read the sequence of floor requests
        int numRestrictedFloors = Integer.parseInt(args[2]); /*read the number of restricted floors
                                                             (the restricted floors are the n highest numbers in floor requests, where n is the number of restricted floors ) */

        if (numFloors == 0) return; //if there's no input, nothing will print

        int elevator1 = floorRequests % 10; //since elevator1 will always move first, set it to the first request
        int elevator2 = 1; //the stationary level is floor1
        
        int topFloor = floorRequests % 10;              //this block is to find the highest floor; it goes through
        for (int j = floorRequests; j > 0; j /= 10) {   //each floor request and compares it a set value (the last number)
            if ((j % 10) > topFloor) {                  //the final value will be the highest number
                topFloor = j % 10;
            }
        }
        
        if (numRestrictedFloors > 0) { //this loop is if there are restricted floors to consider, and if not, the elevator carries on like normal at the else block
            int passcode = Integer.parseInt(args[3]); //read the passcode for the restricted floor
            
            for (int i = floorRequests; i > 0; i /= 10) { //this loop goes through each request 
                int currentFloor = i % 10; //start at the first request and it will move to the next as the loop iterates

                boolean isRestricted = false; //check explanation in notes
                for (int restricted = numFloors; restricted > numFloors - numRestrictedFloors /*this makes sure you're only checking for n restricted floors */; restricted--) { //start with the total number of floors and stop when there's no more possible restricted floors 
                    if (currentFloor == restricted) {                                                          
                        isRestricted = true;
                        break;
                    }
                }

                if (((elevator1 - currentFloor) < (currentFloor - elevator2)) || ((elevator1 - currentFloor) == (currentFloor - elevator2))) { //if elevator1 is closest to the requested floor or equal to it, it will move
                    System.out.println("1 " + currentFloor);
                    elevator1 = currentFloor;

                    if (isRestricted) { //if that requested floor is also restricted it will give the corresponding status
                        if (passcode % numFloors == currentFloor) {
                            System.out.println("Granted");
                        } else {
                            if (passcode % numFloors == 0 && currentFloor == topFloor) {
                                System.out.println("Granted");
                            } else {
                                System.out.println("Denied");
                            }
                        }
                    }
                } else {
                    System.out.println("2 " + currentFloor); //same thing with the elevator1 loop 
                    elevator2 = currentFloor;

                    if (isRestricted) {
                        if (passcode % numFloors == currentFloor) {
                            System.out.println("Granted");
                        } else {
                            if (passcode % numFloors == 0 && currentFloor == topFloor) {
                                System.out.println("Granted");
                            } else {
                                System.out.println("Denied");
                            }
                        }
                    }
                }
            }
        } else { //this is if there's no restricted floors
            System.out.println("1 " + elevator1);
            
            for (int i = floorRequests / 10; i > 0; i /= 10) {
                int currentFloor = i % 10;
                
                if (((elevator1 - currentFloor) < (currentFloor - elevator2)) || ((elevator1 - currentFloor) == (currentFloor - elevator2))) {
                    System.out.println("1 " + currentFloor);
                    elevator1 = currentFloor;
                } else {
                    System.out.println("2 " + currentFloor);
                    elevator2 = currentFloor;
                }
            }
        }
    }
}
