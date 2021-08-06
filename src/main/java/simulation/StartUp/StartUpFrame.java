package simulation.StartUp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
//import jdk.tools.jlink.internal.Platform;
import simulation.Populate.PopulationGraph;

public class StartUpFrame extends javax.swing.JFrame {
    private static boolean javaFxLaunched = false;

    public StartUpFrame() {
        initComponents();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new StartUpFrame().setVisible(true));
    }

    private void populationFrameActionPerformed(java.awt.event.ActionEvent evt) {
        if (!javaFxLaunched) {
            Platform.setImplicitExit(false);
            new Thread(() -> Application.launch(PopulationGraph.class)).start();
            javaFxLaunched = true;
        }else {
            Platform.runLater(() -> {
                try {
                    Application application = PopulationGraph.class.newInstance();
                    Stage primaryStage = new Stage();
                    application.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        simulation.Populate.GUI.demo();
    }
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        pack();
        populationFrameActionPerformed(null);
    }
}
