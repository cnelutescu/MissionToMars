/*
Simulation class that is responsible for reading item data and filling up the rockets.
The Simulation class should include these methods: loadItems, loadU1, loadU2, runSimulation
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class Simulation {
    // Constructor
    Simulation(){
    }

    // method loads all items from text file phase-1.txt and returns an ArrayList of Items
    // loadItems should read the text file line by line and create an Item object for each
    // and then add it to an ArrayList of Items. The method should then return that ArrayList.
    ArrayList <Item> loadItems(ArrayList<Item> phase, int phaseNr) {

        String lineRead;
        String fileName = "";

        try {
            if ( phaseNr == 1){
                System.out.println("Load items for Phase 1");
                fileName = "phase-1.txt";
            } else if (phaseNr == 2){
                System.out.println("Load items for Phase 2");
                fileName = "phase-2.txt";
            } else {
                System.out.println("Can not load Items for Phase-1 or 2, wrong call");
                System.out.println("Program stop");
                System.exit(1);
            }
            File file = new File(fileName);              // create the object file for phase-x.txt
            Scanner scanner = new Scanner(file);                // create the object scanner for object file
            while (scanner.hasNextLine()) {
                Item item = new Item();                     // create an Item object for each
                lineRead = scanner.nextLine();              // read the next line
                int i = lineRead.indexOf("=");              // find index of "="
                item.name = lineRead.substring(0, i);                       // extract the name of item
                item.weight = Integer.parseInt(lineRead.substring(i+1));       // extract the weight of item
                phase.add(item);                            // add it to an ArrayList of Items
            }
            scanner.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return phase;           // The method should then return that ArrayList
    }

    // method takes the ArrayList of Items returned from loadItems and starts creating U1 rockets.
    // It first tries to fill up 1 rocket with as many items as possible before creating a new rocket object
    // and filling that one until all items are loaded.
    // The method then returns the ArrayList of those U1 rockets that are fully loaded.
    ArrayList <U1> loadU1 (ArrayList <Item> items) {
        ArrayList <U1> fleetU1 = new ArrayList<>();      // create ArrayList object fleetU1

        int i = 0;
        while (i < (items.size() - 1)){       // repeat until all items are loaded
            U1 u1 = new U1();                                   // create a new U1 rocket
            while (u1.currentWeight <= u1.rocketMaxWeight) {     // repeat until rocket full
                if (items.get(i).weight > u1.cargoLimit ) {     // item weight > cargo limit?
                    System.out.println("Correct file phase-1.txt item " + items.get(i).name + " from line " + (i+1)
                            + " has weight=" + items.get(i).weight + " which is heavier than cargo limit of U1 rocket=" + u1.cargoLimit);
                    System.out.println("Program stop");
                    System.exit(1);
                }
                if (u1.canCarry(items.get(i))) {                 //  can carry item i ?
                    u1.carry(items.get(i));         // update rocket current weight and cargo carried
                    i++;                    // point next item to load
                    if (i >= items.size()) {
                        u1.rocketStatus = "loaded";     // set status to loaded
                        fleetU1.add(u1);                // add rocket U1 to the fleet
                        break;              // all items from file phase-1.txt are loaded --> exit loop
                    }
                } else {                                        // if can not carry item (is almost full loaded):
                    u1.rocketStatus = "loaded";     // set status to loaded
                    fleetU1.add(u1);                // add rocket U1 to the fleet
                    break;
                }
            }
        }
        return fleetU1;      // The method should then return that ArrayList of those U1 rockets that are fully loaded.
    }

    // method also takes the ArrayList of Items and starts creating U2 rockets and filling them with those items
    // the same way as with U1 until all items are loaded.
    // The method then returns the ArrayList of those U2 rockets that are fully loaded.
    ArrayList <U2> loadU2 (ArrayList <Item> items) {
        ArrayList <U2> fleetU2 = new ArrayList<>();      // create ArrayList object fleetU2

        int i = 0;
        while (i < (items.size() - 1)){       // repeat until all items are loaded
            U2 u2 = new U2();                                   // create a new U2 rocket
            while (u2.currentWeight <= u2.rocketMaxWeight) {     // repeat until rocket full
                if (items.get(i).weight > u2.cargoLimit ) {     // item weight > cargo limit?
                    System.out.println("Correct file phase-1.txt item " + items.get(i).name + " from line " + (i+1)
                            + " has weight=" + items.get(i).weight + " which is heavier than cargo limit of U2 rocket=" + u2.cargoLimit);
                    System.out.println("Program stop");
                    System.exit(1);
                }
                if (u2.canCarry(items.get(i))) {                 //  can carry item i ?
                    u2.carry(items.get(i));         // update rocket current weight and cargo carried
                    i++;                    // point next item to load
                    if (i >= items.size()) {
                        u2.rocketStatus = "loaded";     // set status to loaded
                        fleetU2.add(u2);                // add rocket U2 to the fleet
                        break;              // all items from file phase-1.txt are loaded --> exit loop
                    }
                } else {                                        // if can not carry item (is almost full loaded):
                    u2.rocketStatus = "loaded";     // set status to loaded
                    fleetU2.add(u2);                // add rocket U2 to the fleet
                    break;
                }
            }
        }
        return fleetU2;      // The method should then return that ArrayList of those U2 rockets that are fully loaded.
    }

    // method takes an ArrayList of Rockets and calls launch and land methods for each of the rockets in the ArrayList.
    // Every time a rocket explodes or crashes (i.e if launch or land return false) it will have to send that rocket again.
    // All while keeping track of the total budget required to send each rocket safely to Mars.
    // runSimulation then returns the total budget required to send all rockets (including the crashed ones).
    @SuppressWarnings ("unchecked")

        int runSimulation (ArrayList fleet) {
        int totalBudget;

        Rocket uTemp = new Rocket();        // create new object of type Rocket for getting U1 or U2
        //uTemp = (Rocket) fleet.get(0);    // get object of type U1 or U2 in common object Rocket
        int crtRocket = 1;      // rocket counter
        for (int i = 0; i < fleet.size(); i++) {        // repeat until all fleet landed on Mars
            uTemp = (Rocket) fleet.get(i);
            // launch the rocket i
            if (uTemp.launch()){                     // launch the rocket i
                System.out.println("Rocket " + crtRocket + " successfully launched");
                uTemp.rocketStatus = "launched";     // set status to launched
                // land the rocket i
                if (uTemp.land()) {                   // land the rocket i
                    System.out.println("Rocket " + crtRocket + " successfully landed");
                    uTemp.rocketStatus = "landed";    // set status to landed
                    crtRocket = crtRocket +1;
                } else {
                    System.out.println("Rocket " + crtRocket + " crashed at landing. Repeat the launch with the same cargo.");
                    uTemp.rocketStatus = "crashed";     // set status to crashed
                    // we have to launch another new rocket loaded with the same cargo:
                    fleet = insertNewRocket(fleet, i);
                }
            } else {
                System.out.println("Rocket " + crtRocket + " exploded at launch. Repeat the launch with the same cargo.");
                uTemp.rocketStatus = "exploded";     // set status to exploded
                // we have to launch another new rocket loaded with the same cargo:
                fleet = insertNewRocket(fleet, i);
            }
        }
        totalBudget = fleet.size() * uTemp.rocketCost;
        return totalBudget;
    }

    // Function to insert into the fleet another new rocket loaded with the same cargo:
    @SuppressWarnings ("unchecked")
    private ArrayList <Rocket> insertNewRocket(ArrayList fleet, int index){

//        Rocket uTemp = new Rocket();        // create new object of type Rocket for getting U1 or U2
        Rocket uTemp;        // create new object of type Rocket for getting U1 or U2
        uTemp = (Rocket) fleet.get(index);  // get rocket at index

        if (fleet.get(index) instanceof U1) {
            U1 u1New = new U1();                        // create a new U1 rocket with the same values:
            u1New.rocketStatus = "loaded";              // loaded
            u1New.cargoCarried = uTemp.cargoCarried;    // same cargo carried
            u1New.currentWeight = uTemp.currentWeight;  // same current weight
            fleet.add(index + 1, u1New);                 // insert new rocket U1 at index i+1 to the fleet
        } else if (fleet.get(index) instanceof U2) {
            U2 u2New = new U2();                        // create a new U2 rocket with the same values:
            u2New.rocketStatus = "loaded";              // loaded
            u2New.cargoCarried = uTemp.cargoCarried;
            u2New.currentWeight = uTemp.currentWeight;  // same cargo carried
            fleet.add(index + 1, u2New);                 // same current weight
        } else {
            System.out.println("Can not run simulation, wrong call");
            System.out.println("Program stop");
            System.exit(2);
        }

        return fleet;

    }
}