package simulation;

import config.Helper;
import javafx.application.Application;
import javax.swing.*;

/*
 * This is entry point of the program, need to run this file first else
 * system will throw error like JavaFX runtime components are missing.
 * */
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
        PopulationFrame ap = new PopulationFrame(Helper.getWidth(), Helper.getHeight(), new PopulationGraph());

        f.getContentPane().add(ap);
        f.pack();
        f.setVisible(true);
    }

}
