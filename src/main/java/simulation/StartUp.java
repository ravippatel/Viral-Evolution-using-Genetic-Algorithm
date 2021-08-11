package simulation;

import config.Helper;
import javafx.application.Application;

import javax.swing.*;

public class StartUp {
    public StartUp() {
        showPopulationGraph();
    }

    public static void main(String[] args) {
        new StartUp();
    }

    private void showPopulationGraph() {
        new Thread(() -> Application.launch(PopulationGraph.class)).start();

        JFrame f = new JFrame("Viral Evolution");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PopulationFrame ap = new PopulationFrame(Helper.getWidth(),Helper.getHeight());

        f.getContentPane().add(ap);
        f.pack();
        f.setVisible(true);
    }

}
