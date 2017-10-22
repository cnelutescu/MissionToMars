/*
Create a Main class with the main method and start running the simulation:
1.Create a Simulation object
2.Load Items for Phase-1 and Phase-2
3.Load a fleet of U1 rockets for Phase-1 and then for Phase-2
4.Run the simulation using the fleet of U1 rockets and display the total budget required.
5.Repeat the same for U2 rockets and display the total budget for that.
 */
import java.util.ArrayList;

public class Main {

    public static void main(String args[]){

        ArrayList <Item> itemsPhase1 = new ArrayList<>();      // create ArrayList object itemsPhase1
        ArrayList <Item> itemsPhase2 = new ArrayList<>();      // create ArrayList object itemsPhase2
        Simulation simulation = new Simulation();              // Create a Simulation object
        ArrayList <U1> fleetU1Phase1;                                // declare ArrayList object fleetU1 for phase1
        ArrayList <U2> fleetU2Phase1;                                // declare ArrayList object fleetU2 for phase1
        ArrayList <U1> fleetU1Phase2;                                // declare ArrayList object fleetU1 for phase2
        ArrayList <U2> fleetU2Phase2;                                // declare ArrayList object fleetU2 for phase2

        // Load items from files
        itemsPhase1 = simulation.loadItems(itemsPhase1 , 1);           // Load Items for Phase-1
        itemsPhase2 = simulation.loadItems(itemsPhase2 , 2);           // Load Items for Phase-2

        // Load Fleet for phase 1
        fleetU1Phase1 = simulation.loadU1(itemsPhase1);               // Load a fleet of U1 rockets for Phase-1
        int nrU1Rockets = fleetU1Phase1.size();
        System.out.println("Phase 1 fleet U1 has " + nrU1Rockets + " rockets");
        fleetU2Phase1 = simulation.loadU2(itemsPhase1);               // Load a fleet of U2 rockets for Phase-1
        int nrU2Rockets = fleetU2Phase1.size();
        System.out.println("Phase 1 fleet U2 has " + nrU2Rockets + " rockets");

        // Load Fleet for phase 2
        fleetU1Phase2 = simulation.loadU1(itemsPhase2);               // Load a fleet of U1 rockets for Phase-1
        nrU1Rockets = fleetU1Phase2.size();
        System.out.println("Phase 2 fleet U1 has " + nrU1Rockets + " rockets");
        fleetU2Phase2 = simulation.loadU2(itemsPhase2);               // Load a fleet of U2 rockets for Phase-1
        nrU2Rockets = fleetU2Phase2.size();
        System.out.println("Phase 2 fleet U2 has " + nrU2Rockets + " rockets");

        // Phase 1 -- run simulation using the fleet of U1 rockets and display the total budget required
        System.out.println("Simulation Phase 1 with U1 rockets START ---------");
        int totalBudgetP1U1 = simulation.runSimulation(fleetU1Phase1);
        System.out.println("Simulation Phase 1 using the fleet of U1 rockets: budget = $" + totalBudgetP1U1 + " millions");

        // Phase 2 -- run simulation using the fleet of U1 rockets and display the total budget required
        System.out.println("Simulation Phase 2 with U1 rockets START ---------");
        int totalBudgetP2U1 = simulation.runSimulation(fleetU1Phase2);
        System.out.println("Simulation Phase 2 using the fleet of U1 rockets: budget = $" + totalBudgetP2U1 + " millions");

        int totalBudgetU1 = totalBudgetP1U1 + totalBudgetP2U1;
        System.out.println("Simulation using the fleet of U1 rockets: total budget = $" + totalBudgetU1 + " millions");

        // Phase 1 -- run simulation using the fleet of U2 rockets and display the total budget required
        System.out.println("Simulation Phase 1 with U2 rockets START ---------");
        int totalBudgetP1U2 = simulation.runSimulation(fleetU2Phase1);
        System.out.println("Simulation Phase 1 using the fleet of U1 rockets: budget = $" + totalBudgetP1U2 + " millions");

        // Phase 2 -- run simulation using the fleet of U2 rockets and display the total budget required
        System.out.println("Simulation Phase 2 with U2 rockets START ---------");
        int totalBudgetP2U2 = simulation.runSimulation(fleetU2Phase2);
        System.out.println("Simulation Phase 2 using the fleet of U2 rockets: budget = $" + totalBudgetP2U2 + " millions");

        int totalBudgetU2 = totalBudgetP1U2 + totalBudgetP2U2;
        System.out.println("Simulation using the fleet of U2 rockets: total budget = $" + totalBudgetU2 + " millions");
    }
}