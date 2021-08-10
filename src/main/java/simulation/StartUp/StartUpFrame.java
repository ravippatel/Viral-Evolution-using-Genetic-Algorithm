package simulation.StartUp;

import javafx.application.Application;
import simulation.Populate.GUI;
import simulation.Populate.PopulationGraph;

public class StartUpFrame extends javax.swing.JFrame {
    public StartUpFrame() {
        showPopulationGraph();
    }

    public static void main(String[] args) {
        new StartUpFrame();
    }

    private void showPopulationGraph() {
        new Thread(() -> Application.launch(PopulationGraph.class)).start();
        //new Thread(()-> Application.launch(FitnessGenerationGraph.class)).start();
        GUI.start();
    }

}
