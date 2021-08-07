package geneticAlgorithm;

import java.util.Random;

public class Virus {

        private int fitness = 0;
        String geneType = "ACGT";
        char[] genes = new char[10];
        int geneLength = 10;

        public Virus() {

            Random rn = new Random();

            //Set genes randomly for each individual
            for (int i = 0; i < genes.length; i++) {
                int randIdx = new Random().nextInt(geneType.length());
                char randChar = geneType.charAt(randIdx);
                genes[i] = randChar;
            }
        }

        //Calculate fitness
        public void calcFitness() {
            this.fitness = 0;
            for (int i = 0; i < 10; i++) {
                if (genes[i] == 84) {
                    this.fitness += genes[i];
                }
            }
        }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
