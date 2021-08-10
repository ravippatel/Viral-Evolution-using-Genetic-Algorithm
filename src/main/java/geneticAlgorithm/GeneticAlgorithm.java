package geneticAlgorithm;

import simulation.Populate.PopulationGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
    final List<Integer> generationFitnessList = new ArrayList<>();
    public VirusPopulation virusPopulation = new VirusPopulation();
    private Virus fittest;
    private Virus secondFittest;
    private int generationCount = 0;


    public GeneticAlgorithm(){

    }
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
            fittest.genes[mutationPoint] = 'G';
        } else {
            fittest.genes[mutationPoint] = 'C';
        }

        mutationPoint = rn.nextInt(virusPopulation.viruses[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 65) {
            secondFittest.genes[mutationPoint] = 'T';
        } else {
            secondFittest.genes[mutationPoint] = 'A';
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

    public  Virus runGA(Virus previousGen, PopulationGraph populationGraph, int variantNumber) {
       //
        Random rn = new Random();

        //GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        int gaFitness = previousGen != null ? previousGen.getFitness() : 550;

        //Initialize population
       virusPopulation.initializePopulation(1000);

        //Calculate fitness of each individual
        virusPopulation.calculateFitness();

        System.out.println("Generation: " + generationCount + " Fittest: " + virusPopulation.fittest);
        generationFitnessList.add(virusPopulation.fittest);
        //While population gets an individual with maximum fitness
        while (virusPopulation.fittest < gaFitness) {
            ++generationCount;

            //Do selection
            selection();

            //Do crossover
            crossover();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                mutation();
            }

            //Add fittest offspring to population
            addFittestOffspring();

            //Calculate new fitness value
            virusPopulation.calculateFitness();
            generationFitnessList.add(generationCount, virusPopulation.fittest);

            System.out.println("Generation: " + generationCount + " Fittest: " + virusPopulation.fittest);
        }

        if(variantNumber==1){
            populationGraph.showGenerationFitnessGraphForFirstVariant(new ArrayList<>(generationFitnessList));
        }else{
            populationGraph.showGenerationFitnessGraphForSecondVariant(new ArrayList<>(generationFitnessList));
        }


       // populationGraph.showGenerationFitnessGraph(new ArrayList<>(generationFitnessList));

        System.out.println("\nSolution found in generation: " + generationCount);
        System.out.println("Fitness: "+virusPopulation.getFittest().getFitness());
        System.out.print("Genes: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(virusPopulation.getFittest().genes[i]);
        }

        System.out.println("");

        generationFitnessList.clear();
        generationCount = 0;
        return virusPopulation.getFittest();
    }
}
