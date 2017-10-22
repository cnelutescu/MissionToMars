/*
The U2 Rocket heavier than the U-1 but much safer and can carry a lot more cargo; to a total of 29 tonnes.
However, it costs $120 Million to build and weighs 20 tonnes.  --> in dissonance with specs 18 tonnes (see below!)
It has a greater chance of crashing while landing than while launching,
but just like the U-1 both chances depend on the amount of cargo carried.
Rocket Specifications:
Rocket cost = $120 Million
Rocket weight = 18 Tonnes   --> we will work with 18 not 20! (see up!)
Max weight (with cargo) = 29 Tonnes
Chance of launch explosion = 4% * (cargo carried / cargo limit)
Chance of landing crash = 8% * (cargo carried / cargo limit)

Create classes U1 and U2 that extend the Rocket class and override the land and launch methods
to calculate the corresponding chance of exploding and return either true or false
based on a random number using the probability equation for each.
 */

public class U2 extends Rocket {
    // Constructor
    U2() {
        rocketCost = 120;                    // Rocket cost = $120 Million
        rocketWeight = 18000;                // Rocket weight = 18000 kilograms
        rocketMaxWeight = 29000;             // Rocket Max weight (with cargo) = 29000 kilograms
        rateExplosion = 0.45;                // 4% rate of launch explosion
        rateCrash = 0.55;                    // 8% rate of landing crash
        cargoLimit = rocketMaxWeight - rocketWeight;
        currentWeight = rocketWeight;
        //random = new Random().nextDouble();     // generates a new random number between 0 and 0.9999999999....
    }
    // override the launch method to calculate the corresponding chance of exploding
    public boolean launch() {
        this.chanceLaunchExplosion = rateExplosion * ((double)cargoCarried / (double)cargoLimit);
        return !(this.chanceLaunchExplosion >= this.random);
    }
    // override the land method to calculate the corresponding chance of crash
    public boolean land() {
        this.chanceLandingCrash = rateCrash * ((double)cargoCarried / (double)cargoLimit);
        return !(this.chanceLandingCrash >= this.random);
    }
}
