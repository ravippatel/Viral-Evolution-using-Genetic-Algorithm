package geneticAlgorithm;

import config.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Virus {

    String geneType = "ACGT";
    char[] genes = new char[10];
    int geneLength = 10;
    private int fitness = 0;

    public Virus() {

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

        List<Integer> fitnessFunctions = new ArrayList<>();
        fitnessFunctions.add(naiveA1Fitness());
        fitnessFunctions.add(naiveA2Fitness());
        fitnessFunctions.add(naiveB1Fitness());
        fitnessFunctions.add(naiveB2Fitness());
        fitnessFunctions.add(recoveredA1Fitness());
        fitnessFunctions.add(recoveredA2Fitness());
        fitnessFunctions.add(recoveredB1Fitness());
        fitnessFunctions.add(recoveredB2Fitness());
        fitnessFunctions.add(vaccinatedA1Fitness());
        fitnessFunctions.add(vaccinatedA2Fitness());
        fitnessFunctions.add(vaccinatedB1Fitness());
        fitnessFunctions.add(vaccinatedB2Fitness());
        Config.hashtable.put(String.valueOf(genes), fitnessFunctions);
        Collections.sort(fitnessFunctions);
        Collections.reverse(fitnessFunctions);
        //this.fitness = (int) fitnessFunctions.stream().mapToInt((x) -> x).average().orElse(0) + 150;
        //this.fitness = fitnessFunctions.stream().mapToInt((x) -> x).max().orElse(0);
        this.fitness = fitnessFunctions.get(3);
    }

    public int getFitness() {
        return fitness;
    }

    public int naiveA1Fitness() {
        int nA1 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 65) {
                nA1 += genes[i];
            }
        }
        return nA1;
    }

    public int naiveA2Fitness() {
        int nA2 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 67) {
                nA2 += genes[i];
            }
        }
        return nA2;
    }

    public int naiveB1Fitness() {
        int nB1 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 65 || genes[i] == 67) {
                nB1 += genes[i];
            }
        }
        return nB1;
    }

    public int naiveB2Fitness() {
        int nB2 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 71) {
                nB2 += genes[i];
            }
        }
        return nB2;
    }

    public int recoveredA1Fitness() {
        int nA1 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 65 || genes[i] == 71) {
                nA1 += genes[i];
            }
        }
        return nA1;
    }

    public int recoveredA2Fitness() {
        int nA2 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 67 || genes[i] == 71) {
                nA2 += genes[i];
            }
        }
        return nA2;
    }

    public int recoveredB1Fitness() {
        int nB1 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 65 || genes[i] == 67 || genes[i] == 84) {
                nB1 += genes[i];
            }
        }
        return nB1;
    }

    public int recoveredB2Fitness() {
        int nB2 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 65 || genes[i] == 67 || genes[i] == 71) {
                nB2 += genes[i];
            }
        }
        return nB2;
    }

    public int vaccinatedA1Fitness() {
        int nA1 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 84 || genes[i] == 65) {
                nA1 += genes[i];
            }
        }
        return nA1;
    }

    public int vaccinatedA2Fitness() {
        int nA2 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 84 || genes[i] == 67) {
                nA2 += genes[i];
            }
        }
        return nA2;
    }

    public int vaccinatedB1Fitness() {
        int nB1 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 84 || genes[i] == 71) {
                nB1 += genes[i];
            }
        }
        return nB1;
    }

    public int vaccinatedB2Fitness() {
        int nB2 = 0;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == 84) {
                nB2 += genes[i];
            }
        }
        return nB2;
    }


}
