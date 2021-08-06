package simulation.Populate;

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

    private final int Dots_Size = 10;
    private final int infectDistance = 10;

    private final int DISTANCE_FOR_INFECTION = 10;

    private final int height;
    private final int width;

    private final Random gen = new Random();
    private final simulation.Populate.PopulationGraph PopulationGraph = new PopulationGraph();


    public PopulationFrame(int height, int width) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        for (int i = 0; i < population; i++) {
            int x = gen.nextInt(width);
            int y = gen.nextInt(height);
            p[i] = new Person(x, y);
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
            if(p[i].status== PersonStatus.INFECTED){
                g.setColor(Color.red);
            }else{
                g.setColor(Color.gray);

            }
            g.fillOval(p[i].x, p[i].y, Dots_Size, Dots_Size);
        }
    }

    public int infected(){
        int infected = 0;
        for(int i=0; i<population; i++){
            if(p[i].status== PersonStatus.INFECTED){
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
        PopulationGraph.showChartWithLockDown (infected(), population);
        repaint();
    }
}
