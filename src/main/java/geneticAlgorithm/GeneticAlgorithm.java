package geneticAlgorithm;

import java.util.Random;

public class GeneticAlgorithm {

    public VirusPopulation virusPopulation = new VirusPopulation();
    private Virus fittest;
    private Virus secondFittest;
    private int generationCount = 0;

//    public static void main(String[] args) {
//        this.runGA();
//
//    }

    //Selection
    void selection() {

        //Select the most fittest individual
        fittest = virusPopulation.getFittest();

        //Select the second most fittest individual
        secondFittest = virusPopulation.getSecondFittest();
    }

    //Crossover
    void crossover() {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(virusPopulation.viruses[0].geneLength);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            char temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }

    //Mutation
    void mutation() {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(virusPopulation.viruses[0].geneLength);

        //Flip values at the mutation point
        if (fittest.genes[mutationPoint] == 71) {
            fittest.genes[mutationPoint] = 'A';
        } else {
            fittest.genes[mutationPoint] = 'T';
        }

        mutationPoint = rn.nextInt(virusPopulation.viruses[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 65) {
            secondFittest.genes[mutationPoint] = 'T';
        } else {
            secondFittest.genes[mutationPoint] = 'G';
        }
    }

    //Get fittest offspring
    Virus getFittestOffspring() {
        if (fittest.getFitness() > secondFittest.getFitness()) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Get index of least fit individual
        int leastFittestIndex = virusPopulation.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        virusPopulation.viruses[leastFittestIndex] = getFittestOffspring();
    }


    public int getGenerationCount() {
        return generationCount;
    }

    public void setGenerationCount(int generationCount) {
        this.generationCount = generationCount;
    }

    public static Virus runGA() {
        Random rn = new Random();

        GeneticAlgorithm demo = new GeneticAlgorithm();

        //Initialize population
        demo.virusPopulation.initializePopulation(1000);

        //Calculate fitness of each individual
        demo.virusPopulation.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.virusPopulation.fittest);

        //While population gets an individual with maximum fitness
        while (demo.virusPopulation.fittest < 600) {
            ++demo.generationCount;

            //Do selection
            demo.selection();

            //Do crossover
            demo.crossover();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                demo.mutation();
            }

            //Add fittest offspring to population
            demo.addFittestOffspring();

            //Calculate new fitness value
            demo.virusPopulation.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.virusPopulation.fittest);
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+demo.virusPopulation.getFittest().getFitness());
        System.out.print("Genes: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(demo.virusPopulation.getFittest().genes[i]);
        }

        System.out.println("");

        return demo.virusPopulation.getFittest();
    }
}
