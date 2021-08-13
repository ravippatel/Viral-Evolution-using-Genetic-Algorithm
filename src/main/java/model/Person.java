package model;

import config.Constant;
import config.Helper;

import java.util.Random;

public class Person {
    private final Random random = new Random();
    public int x;
    public int y;
    public boolean can_move = true;
    public boolean immune = false;
    public double fitness = 0;
    public double actual_fitness = 0;
    public boolean main_virus =false;
    public boolean gen1_virus =false;
    public String gene = "";
    public boolean delta_variant = false;
    public boolean vaccinated = false;
    public boolean recovered = false;
    public boolean infected = false;
    public boolean infected_main = false;
    public boolean infected_gen1 = false;
    public boolean infected_delta = false;
    public int no_infected_days = 0;
    public int immune_days = 0;
    public boolean died = false;
    public int infected_duration = 700;
    private int vel_x = 0;
    private int vel_y = 0;

    public int getVel_x() {
        return vel_x;
    }

    public void setVel_x(int vel_x) {
        this.vel_x = vel_x;
    }

    public int getVel_y() {
        return vel_y;
    }

    public void setVel_y(int vel_y) {
        this.vel_y = vel_y;
    }

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
        vel_x = random.nextInt(10) - 5;
        vel_y = random.nextInt(10) - 5;
    }

    public void move() {
        if (can_move) {
            x += vel_x;
            y += vel_y;
            int width = Helper.getWidth();
            if (x > width || x < 0) {
                vel_x = -vel_x;
            }

            int height = Helper.getHeight();
            if (y > height|| y < Constant.FRAME_MIN_HEIGHT) {
                vel_y = -vel_y;
            }
        }
    }

    public void checkForImmunity(int total_days) {
        if (no_infected_days > 0) {
            no_infected_days++;
            fitness -= 0.1;
        }

        if (no_infected_days == infected_duration - 1) {
            deathProbability();
        }
        if (no_infected_days > infected_duration && !died) {
            no_infected_days = 0;
            fitness = actual_fitness + random.nextInt(20) - 10;
            recoveredHostPopulation();
            immune = true;
        }
//        if (total_days > 1500 && recovered && vaccinated && !died) {
//            delta_variant = true;
//        }
        if (immune) {
            if (immune_days++ == 70)
                vaccinateHostPopulation();
        }
        /*if (total_days > 1200 && !infected_main && !infected_gen1 && !infected_delta) {
            vaccinated = true;

        }*/
    }

    public void deathProbability() {
        int random_number = random.nextInt(100);
        died = random_number >= 1 && random_number <= 2;
    }

    public void vaccinateHostPopulation() {
        // if (fitness<600){
        vaccinated = true;
        fitness += 89;
        //  }

    }

    public void recoveredHostPopulation() {
        //if(fitness >= 650) {
        recovered = true;
        // }
    }
}
