/*
The U-1 Rocket is light weight, agile and pretty safe, but can only carry a total of 18 tonnes of cargo.
It costs $60 Million to build and weighs 10 tonnes.
It has a slim chance of crashing while landing but a bigger chance of exploding when launching,
both chances depend on the amount of cargo carried in the rocket.
Rocket Specifications:
Rocket cost = $100 Million
Rocket weight = 10 Tonnes
Max weight (with cargo) = 18 Tonnes
Chance of launch explosion = 5% * (cargo carried / cargo limit)
Chance of landing crash = 1% * (cargo carried / cargo limit)

Create classes U1 and U2 that extend the Rocket class and override the land and launch methods
to calculate the corresponding chance of exploding and return either true or false
based on a random number using the probability equation for each.
 */

public class U1 extends Rocket {
    // Constructor
    U1() {
        rocketCost = 100;                    // Rocket cost = $100 Million
        rocketWeight = 10000;                // Rocket weight = 10000 kilograms
        rocketMaxWeight = 18000;             // Rocket Max weight (with cargo) = 18000 kilograms
        rateExplosion = 0.49;                // 5% rate of launch explosion
        rateCrash = 0.53;                    // 1% rate of landing crash
        cargoLimit = rocketMaxWeight - rocketWeight;
        currentWeight = rocketWeight;
        //random = new Random().nextDouble();     // generates a new random number between 0 and 0.9999999999....
    }
    // override the launch method to calculate the corresponding chance of exploding
    public boolean launch() {
        this.chanceLaunchExplosion = rateExplosion *  ( (double)cargoCarried / (double)cargoLimit);
        return !(this.chanceLaunchExplosion >= this.random);
    }
    // override the land method to calculate the corresponding chance of crash
    public boolean land() {
        this.chanceLandingCrash = rateCrash * ((double)cargoCarried / (double)cargoLimit);
        return !(this.chanceLandingCrash >= this.random);
    }
}
