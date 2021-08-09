package simulation.Populate;

import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.Virus;
import model.Person;
import model.PersonStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.Random;

public class PopulationFrame extends JPanel implements ActionListener {

    private final Timer TM = new Timer(100, this);

    private int population = 1000;

    int moveablePopulation = (population * 30) / 100;

    private final Person[] p = new Person[population];

    private final int Dots_Size = 10;
    private final int infectDistance = 10;

    private final int DISTANCE_FOR_INFECTION = 10;
    private static int total_days=0;

    private final int height;
    private final int width;

    private final Random gen = new Random();
    private int firstNewVariantFitness = 0;
    private int secondNewVariantFitness = 0;
    private Virus firstNewVariant;
    private final simulation.Populate.PopulationGraph PopulationGraph = new PopulationGraph();


    public PopulationFrame(int height, int width) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        for (int i = 0; i < population; i++) {
            int x = gen.nextInt(width);
            int y = gen.nextInt(height);
            p[i] = new Person(x, y);
            p[i].actual_fitness = getRandomFitness(500, 700);
            p[i].fitness=p[i].actual_fitness;
            p[i].gene = getRandomGenoType();
        }
        firstNewVariant = getNewVariant(null);
        firstNewVariantFitness = firstNewVariant.getFitness();
        secondNewVariantFitness = getNewVariant(firstNewVariant).getFitness();
        p[0].status = PersonStatus.INFECTED;
        p[0].no_infected_days=1;
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
            if(p[i].fitness <= 550){
                g.setColor(Color.gray);
            } else if(p[i].fitness <= 600){
                g.setColor(Color.gray.darker());
            } else if(p[i].fitness <= 650){
                g.setColor(Color.gray.darker().darker());
            } else if(p[i].fitness <= 700){
                g.setColor(Color.gray.darker().darker().darker());
            }

            if(p[i].infected && p[i].fitness <= 600){
                g.setColor(Color.red);
            }

            if(p[i].infected && p[i].fitness <= firstNewVariantFitness){
                g.setColor(Color.orange);
            }
            if(p[i].recovered){
                g.setColor(Color.magenta);
            }
            if(p[i].vaccinated)
            {
                g.setColor(Color.pink);
            }
            if(p[i].died)
            {
                g.setColor(Color.BLUE);
            }
            if(p[i].second_variant &&p[i].infected && p[i].fitness<secondNewVariantFitness)
            {
                g.setColor(Color.CYAN);

            }
            g.fillOval(p[i].x, p[i].y, Dots_Size, Dots_Size);

        }
    }
    public int infected(){
        int infected = 0;
        for(int i=0; i<population; i++){
            if(p[i].infected){
                infected++;
            }
            if(p[i].recovered){
                infected--;
            }
        }
        return infected;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        total_days++;
        for(int i=0;i<population;i++){

            p[i].move();
            p[i].checkForImmunity(total_days);
        }
        checkDistance();
        PopulationGraph.showChartVirusEvolution(infected(), population);
        System.out.println("days="+(total_days));

        repaint();
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

    public Virus getNewVariant(Virus previousVariant) {
        return  GeneticAlgorithm.runGA(previousVariant);
    }


    public void checkDistance() {
        // compare each point to all the other points
        for(int i=0;i<population;i++) {
            for(int j=i+1;j<population;j++) {
                int deltax = p[i].x - p[j].x;
                int deltay = p[i].y - p[j].y;
                double dist = Math.sqrt(deltax*deltax+deltay*deltay);
                // if the distance between 2 points is small enough, and one of
                // the Persons is infected, then infect the other Person
                if (dist < infectDistance) {
                    if (p[i].fitness <= 500 ) {
                        p[j].infected = true;
//                        p[i].no_infected_days++;
                        p[j].no_infected_days++;
                    }
                    if(p[j].second_variant){
                        p[j].infected=true;
                    }


                }
            }
        }
    }
}
