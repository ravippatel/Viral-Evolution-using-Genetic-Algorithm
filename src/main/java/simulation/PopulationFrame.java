package simulation;

import config.Constant;
import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.Virus;
import model.Person;
import model.PersonStatus;

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
    private final PopulationGraph populationGraph = new PopulationGraph();
    private final GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
    private final int population = Constant.hostPopulation;
    private final Person[] p = new Person[population];
    private final Virus firstNewVariant;
    private int firstNewVariantFitness = 0;
    private int secondNewVariantFitness = 0;


    public PopulationFrame(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        for (int i = 0; i < population; i++) {
            int x = gen.nextInt(width);
            int y = gen.nextInt((height - Constant.FRAME_MIN_HEIGHT) + 1) + Constant.FRAME_MIN_HEIGHT;
            p[i] = new Person(x, y);
            p[i].actual_fitness = getRandomFitness(500, 700);
            p[i].fitness = p[i].actual_fitness;
            p[i].gene = getRandomGenoType();
        }

        firstNewVariant = getNewVariant(null, 1);
        firstNewVariantFitness = firstNewVariant.getFitness();

        p[0].status = PersonStatus.INFECTED;
        p[0].no_infected_days = 1;
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
        for (int i = 0; i < population; i++) {
            if (p[i].fitness <= 550) {
                Color NaiveColor = Color.decode("#bdc3c7");
                g.setColor(NaiveColor);
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                g.drawString("Naive", 25, Constant.TEXT_POSITION);

            } else if (p[i].fitness <= 600) {
                // g.setColor(Color.gray.darker());

                Color NaiveColor = Color.decode("#bdc3c7");
                g.setColor(NaiveColor);
            } else if (p[i].fitness <= 650) {
                //g.setColor(Color.gray.darker().darker());

                Color NaiveColor = Color.decode("#bdc3c7");
                g.setColor(NaiveColor);
            } else if (p[i].fitness <= 700) {
                //g.setColor(Color.gray.darker().darker().darker());

                Color NaiveColor = Color.decode("#bdc3c7");
                g.setColor(NaiveColor);
            }

            if (p[i].infected && p[i].fitness <= 600) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                g.setColor(Color.red);
                Color InfectedOneColor = Color.decode("#c0392b");
                g.setColor(InfectedOneColor);
                g.drawString("Generation 1", 110, Constant.TEXT_POSITION);
            }

            if (p[i].infected && p[i].fitness <= firstNewVariantFitness) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                Color InfectedTwoColor = Color.decode("#9b59b6");
                g.setColor(InfectedTwoColor);
                g.drawString("Generation 2", 260, Constant.TEXT_POSITION);
            }
            if (p[i].recovered) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                //g.setColor(Color.orange);
                Color RecoveredColor = Color.decode("#e67e22");
                g.setColor(RecoveredColor);

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

                g.drawString("Died", 710, Constant.TEXT_POSITION);
            }
            if (p[i].second_variant && p[i].infected && p[i].fitness < secondNewVariantFitness) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, Constant.TEXT_HEIGHT));
                //g.setColor(Color.BLUE);
                Color Midnight = Color.decode("#2c3e50");
                g.setColor(Midnight);
                g.drawString("Delta Variant", 800, Constant.TEXT_POSITION);

            }
            g.fillOval(p[i].x, p[i].y, Constant.DOTS_SIZE, Constant.DOTS_SIZE);

        }
    }

    public int infected() {
        int infected = 0;
        for (int i = 0; i < population; i++) {
            if (p[i].infected) {
                infected++;
            }
            if (p[i].recovered) {
                infected--;
            }
        }
        return infected;
    }

    public int totalRecovered() {
        int recovered = 0;
        for (int i = 0; i < population; i++) {
            if (p[i].recovered) {
                recovered++;
            }
            //if(p[i].infected){
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
        if (total_days <= 730) {
            total_days++;

            for (int i = 0; i < population; i++) {
                p[i].move();
                p[i].checkForImmunity(total_days);
            }

            if (total_days == 100) {
                secondNewVariantFitness = getNewVariant(firstNewVariant, 2).getFitness();
            }
            checkDistance();
            PopulationGraph.showChartVirusEvolution(infected(), population, totalRecovered(), totalVaccinated(), totalDied(), total_days);
            System.out.println("Days: " + (total_days));

            repaint();
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

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


    public void checkDistance() {
        // compare each point to all the other points
        for (int i = 0; i < population; i++) {
            for (int j = i + 1; j < population; j++) {
                int deltax = p[i].x - p[j].x;
                int deltay = p[i].y - p[j].y;
                double dist = Math.sqrt(deltax * deltax + deltay * deltay);
                // if the distance between 2 points is small enough, and one of
                // the Persons is infected, then infect the other Person
                if (dist < Constant.INFECT_DISTANCE) {
                    if (p[i].fitness <= 500) {
                        p[j].infected = true;
//                        p[i].no_infected_days++;
                        p[j].no_infected_days++;
                    }
                    if (p[j].second_variant) {
                        p[j].infected = true;
                    }
                }
            }
        }
    }
}
