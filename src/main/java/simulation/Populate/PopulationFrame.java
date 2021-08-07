package simulation.Populate;

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

    private final Timer TM = new Timer(100, this);

    private int population = 1000;

    int moveablePopulation = (population * 30) / 100;

    private final Person[] p = new Person[population];

    private final int Dots_Size = 20;
    private final int infectDistance = 10;

    private final int DISTANCE_FOR_INFECTION = 10;

    private final int height;
    private final int width;

    private final Random gen = new Random();
    private int virusFitness = getNewVariant().getFitness();
    private final simulation.Populate.PopulationGraph PopulationGraph = new PopulationGraph();


    public PopulationFrame(int height, int width) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        for (int i = 0; i < population; i++) {
            int x = gen.nextInt(width);
            int y = gen.nextInt(height);
            p[i] = new Person(x, y);
            p[i].fitness = getRandomFitness(500, 700);
            p[i].gene = getRandomGenoType();
        }
        p[0].status = PersonStatus.INFECTED;
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
                g.setColor(Color.BLUE);
            }else if(p[i].fitness <= 600){
                g.setColor(Color.blue.darker());
            } else if(p[i].fitness <= 650){
                g.setColor(Color.blue.darker().darker());
            }else if(p[i].fitness <= 700){
                g.setColor(Color.blue.darker().darker().darker());
            }
            g.fillOval(p[i].x, p[i].y, Dots_Size, Dots_Size);
        }
    }

    public int infected(){
        int infected = 0;
        for(int i=0; i<population; i++){
            if(p[i].fitness < virusFitness){
                infected++;
            }
        }
        return infected;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<population;i++){
            p[i].move();
        }
        PopulationGraph.showChartVirusEvolution (infected(), population);
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

    public Virus getNewVariant() {
        return  GeneticAlgorithm.runGA();
    }
}
