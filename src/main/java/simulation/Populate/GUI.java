package simulation.Populate;

import javax.swing.*;

public class GUI {
    public static void demo(){
        int height = 600;
        int width = 800;
        JFrame f = new JFrame("Viral Evolution");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PopulationFrame ap = new PopulationFrame(height, width);
        f.getContentPane().add(ap);
        f.pack();
        f.setVisible(true);
    }

}
