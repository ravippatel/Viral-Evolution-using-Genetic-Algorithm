package simulation;

import config.Constant;
import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.Virus;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PopulationFrame extends JPanel implements ActionListener {

    private static int total_days = 0;
    private final Timer TM = new Timer(100, this);
    private final int height;
    private final int width;


    private final Random gen = new Random();
    private  PopulationGraph populationGraph;
    private final GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
    private final int population = Constant.hostPopulation;
    private final Person[] p = new Person[population];
    private final Virus firstVariant;
    private Virus secondVariant;
    private Virus thirdVariant;
    private int firstVariantFitness = 0;
    private int secondVariantFitness = 1000;
    private int thirdVariantFitness = 1000;

//    public void printPerson(Person p, int i) {
//        System.out.print(i+"\t"+p.fitness+"\t"+p.actual_fitness+"\t"+p.main_virus +"\t"+p.gen1_virus +"\t"+p.infected_main+"\t"+p.infected_gen1);
//    }

    public PopulationFrame(int width, int height, PopulationGraph populationGraph) {
        this.width = width;
        this.height = height;
        this.populationGraph = populationGraph;
        setPreferredSize(new Dimension(width, height));
        for (int i = 0; i < population; i++) {
            int x = gen.nextInt(width);
            int y = gen.nextInt((height - Constant.FRAME_MIN_HEIGHT) + 1) + Constant.FRAME_MIN_HEIGHT;
            p[i] = new Person(x, y);
            if(i<population/2) {
                p[i].actual_fitness = getRandomFitness(500, 600);
            }
           else{
                p[i].actual_fitness = getRandomFitness(600, 700);
            }
            p[i].fitness = p[i].actual_fitness;
            p[i].gene = getRandomGenoType();
        }

        firstVariant = getNewVariant(null, 1);
        firstVariantFitness = firstVariant.getFitness();

        System.out.println("first fitness"+ firstVariantFitness);
//        p[1].status = PersonStatus.INFECTED;
//        p[1].main_virus=true;
//        p[1].no_infected_days = 1;
        for (int i = 0; i < 10; i++) {
            int rand = gen.nextInt(1000);
            p[rand].infected=true;
            p[rand].main_virus=true;
            p[rand].no_infected_days = 1;
//            p[50].setVel_x(p[50].getVel_x() * 10);
//            p[50].setVel_y(p[50].getVel_y() * 10);
        }


        TM.start();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color NaiveColor = Color.decode("#bdc3c7");
        for (int i = 0; i < population; i++) {
            g.setColor(NaiveColor);
            g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
            g.drawString("Naive", 25, Constant.TEXT_POSITION);
//
//            if (p[i].fitness <= 550) {
//                g.setColor(NaiveColor);
//                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
//                g.drawString("Naive", 25, Constant.TEXT_POSITION);
//
//            } else if (p[i].fitness <= 600) {
//                // g.setColor(Color.gray.darker());
//
////                Color NaiveColor = Color.decode("#bdc3c7");
//                g.setColor(NaiveColor);
//            } else if (p[i].fitness <= 650) {
//                //g.setColor(Color.gray.darker().darker());
//
////                Color NaiveColor = Color.decode("#bdc3c7");
//                g.setColor(NaiveColor);
//            } else if (p[i].fitness <= 700) {
//                //g.setColor(Color.gray.darker().darker().darker());
//
////                Color NaiveColor = Color.decode("#bdc3c7");
//                g.setColor(NaiveColor);
//            }

            if ((p[i].infected_main && p[i].fitness <= firstVariantFitness) || p[i].main_virus ) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                g.setColor(Color.red);
                Color InfectedOneColor = Color.decode("#c0392b");
                g.setColor(InfectedOneColor);
                g.drawString("Generation 1", 110, Constant.TEXT_POSITION);

                    p[i].main_virus =true;
            }
            if(((p[i].infected_gen1 && p[i].fitness< secondVariantFitness) || p[i].gen1_virus )&& !p[i].main_virus) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                Color InfectedTwoColor = Color.decode("#9b59b6");
                g.setColor(InfectedTwoColor);
                g.drawString("Generation 2", 260, Constant.TEXT_POSITION);
//                if(total_days>300)
                    p[i].gen1_virus = true;
            }
            if (p[i].recovered) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                //g.setColor(Color.orange);
                Color RecoveredColor = Color.decode("#e67e22");
                g.setColor(RecoveredColor);
                p[i].gen1_virus = false;
                p[i].main_virus = false;
                g.drawString("Recovered", 410, Constant.TEXT_POSITION);
            }
            if (p[i].vaccinated) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                Color VaccinatedColor = Color.decode("#2ecc71");
                g.setColor(VaccinatedColor);
                //g.setColor(Color.GREEN);
                g.drawString("Vaccinated", 560, Constant.TEXT_POSITION);
            }
            if (p[i].died) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                //g.setColor(Color.CYAN);
                Color Belize = Color.decode("#3498db");
                g.setColor(Belize);
                p[i].can_move = false;
                g.drawString("Died", 710, Constant.TEXT_POSITION);
            }
            if (((p[i].infected_delta && p[i].fitness < thirdVariantFitness) || p[i].delta_variant) && !p[i].main_virus && !p[i].gen1_virus) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                //g.setColor(Color.BLUE);
                Color Midnight = Color.decode("#2c3e50");
                g.setColor(Midnight);
                p[i].delta_variant = true;
                g.drawString("Delta Variant", 800, Constant.TEXT_POSITION);

            }
            g.fillOval(p[i].x, p[i].y, Constant.DOTS_SIZE, Constant.DOTS_SIZE);
        }
        if(total_days==400) {
            for (int k = 0; k < 10; k++) {
                int rand = gen.nextInt(1000);
                if(p[rand].died || p[rand].main_virus) {
                    k--;
                    continue;
                }
                p[rand].infected=true;
                p[rand].gen1_virus=true;
                p[rand].no_infected_days = 1;
            }
        }
        if(total_days==1500) {
            for (int k = 0; k < 5; k++) {
                int rand = gen.nextInt(1000);
                if(p[rand].died || p[rand].main_virus || p[rand].gen1_virus) {
                    k--;
                    continue;
                }
                p[rand].infected=true;
                p[rand].delta_variant=true;
                p[rand].no_infected_days = 1;
            }
        }
    }
    public void checkDistance() {
        // compare each point to all the other points
        for (int i = 0; i < population; i++) {
            for (int j = i + 1; j < population; j++) {
                int deltax = p[i].x - p[j].x;
                int deltay = p[i].y - p[j].y;
                double dist = Math.sqrt(deltax * deltax + deltay * deltay);
                // if the distance between 2 points is small enough, and one of
                // the Persons is totalInfected, then infect the other Person
                if (dist <= Constant.INFECT_DISTANCE) {
                    if (p[j].main_virus) {
//                        System.out.println(j+" infected "+i);
                        p[i].infected_main = true;
//                        p[i].no_infected_days++;
                    }
                    if(p[j].gen1_virus)
                        p[i].infected_gen1 = true;
                    if(p[j].delta_variant)
                        p[i].infected_delta = true;
                    if(p[i].infected_gen1 || p[i].infected_main|| p[i].infected_delta)
                        p[i].no_infected_days++;
//                    if (p[j].delta_variant) {
//                        p[j].infected = true;
//                    }
                }
            }
        }
    }

    public int infected() {
        int infected = 0;
        for (int i = 0; i < population; i++) {
            if (p[i].infected_main||p[i].infected_gen1||p[i].infected_delta) {
                infected++;
            }
            if (p[i].recovered) {
                infected--;
            }
        }
        return infected;
    }
//
//    public int infected_main() {
//        int infected = 0;
//        for (int i = 0; i < population; i++) {
//            if (p[i].infected_main) {
//                infected++;
//            }
//            if (p[i].recovered) {
//                infected--;
//            }
//        }
//        return infected;
//    }
//
//    public int infected_gen1() {
//        int infected = 0;
//        for (int i = 0; i < population; i++) {
//            if (p[i].infected_gen1) {
//                infected++;
//            }
//            if (p[i].recovered) {
//                infected--;
//            }
//        }
//        return infected;
//    }
//
//    public int infected_delta() {
//        int infected = 0;
//        for (int i = 0; i < population; i++) {
//            if (p[i].infected_delta) {
//                infected++;
//            }
//            if (p[i].recovered) {
//                infected--;
//            }
//        }
//
////        System.out.println("Infected: " + infected);
//
//        return infected;
//    }

    public int totalRecovered() {
        int recovered = 0;
        for (int i = 0; i < population; i++) {
            if (p[i].recovered) {
                recovered++;
            }
            //if(p[i].totalInfected){
            //recovered--;
            //}
        }
        return recovered;
    }

    public int totalVaccinated() {
        int vaccinated = 0;
        for (int i = 0; i < population; i++) {
            if (p[i].vaccinated) {
                vaccinated++;
            }
        }
        return vaccinated;
    }

    public int totalDied() {
        int died = 0;
        for (int i = 0; i < population; i++) {
            if (p[i].died) {
                died++;
            }
        }
        return died;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            total_days++;

            for (int i = 0; i < population; i++) {
                p[i].move();
                p[i].checkForImmunity(total_days);
            }

            if (total_days == 350) {
                secondVariant = getNewVariant(firstVariant, 2);
                secondVariantFitness = secondVariant.getFitness();
            }
            if (total_days == 900) {
                thirdVariant = getNewVariant(secondVariant, 3);
                thirdVariantFitness = thirdVariant.getFitness();
            }
            checkDistance();
            PopulationGraph.showChartVirusEvolution(infected(), population, totalRecovered(), totalVaccinated(), totalDied(), total_days);
//            System.out.println("Days: " + (total_days)+"\t"+firstNewVariantFitness+"\t"+secondNewVariantFitness);

//            System.out.print("\n"+total_days+"\t");
//            printPerson(p[0],0);
//            System.out.print("\n"+total_days+"\t");
//            printPerson(p[1],1);
//            System.out.print("\n"+total_days+"\t");
//            printPerson(p[2],2);
//            System.out.print("\n"+total_days+"\t");
//            printPerson(p[3],3);

            repaint();
//            try {
//                Thread.sleep(250);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }


    }

    public int getRandomFitness(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public String getRandomGenoType() {
        String[] geneType = {"A1", "A2", "B1", "B2"};
        int randIdx = new Random().nextInt(4);
        return geneType[randIdx];
    }

    public Virus getNewVariant(Virus previousVariant, int variantNumber) {
        return geneticAlgorithm.runGA(previousVariant, populationGraph, variantNumber);
    }



}
