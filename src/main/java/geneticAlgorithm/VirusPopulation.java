package geneticAlgorithm;

class VirusPopulation {

    int popSize = 10;
    Virus[] viruses = new Virus[10];
    int fittest = 0;

    //Initialize virus population
    public void initializePopulation(int size) {
        for (int i = 0; i < viruses.length; i++) {
            viruses[i] = new Virus();
        }
    }

    //Get the fittest virus
    public Virus getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < viruses.length; i++) {
            if (maxFit <= viruses[i].getFitness()) {
                maxFit = viruses[i].getFitness();
                maxFitIndex = i;
            }
        }
        fittest = viruses[maxFitIndex].getFitness();
        return viruses[maxFitIndex];
    }

    //Get the second most fittest virus
    public Virus getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < viruses.length; i++) {
            if (viruses[i].getFitness() > viruses[maxFit1].getFitness()) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (viruses[i].getFitness() > viruses[maxFit2].getFitness()) {
                maxFit2 = i;
            }
        }
        return viruses[maxFit2];
    }

    //Get index of least fittest virus
    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < viruses.length; i++) {
            if (minFitVal >= viruses[i].getFitness()) {
                minFitVal = viruses[i].getFitness();
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    //Calculate fitness of each virus
    public void calculateFitness() {

        for (int i = 0; i < viruses.length; i++) {
            viruses[i].calcFitness();
        }
        getFittest();
    }

}
