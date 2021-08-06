package geneticAlgorithm;

import java.util.Random;

public class Individual {

        int fitness = 0;
        String geneType = "ACGT";
        char[] genes = new char[10];
        int geneLength = 10;

        public Individual() {

            Random rn = new Random();

            //Set genes randomly for each individual
            for (int i = 0; i < genes.length; i++) {
                int randIdx = new Random().nextInt(geneType.length());
                char randChar = geneType.charAt(randIdx);
                genes[i] = randChar;
            }

            fitness = 0;
        }

        //Calculate fitness
        public void calcFitness() {

            fitness = 0;
            for (int i = 0; i < 10; i++) {
                if (genes[i] == 84) {
                    ++fitness;
                }
            }
        }

}
