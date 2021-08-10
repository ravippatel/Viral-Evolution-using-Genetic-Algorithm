package simulation.Populate;

import config.Helper;

import javax.swing.*;

public class GUI {
    public static void start(){
        JFrame f = new JFrame("Viral Evolution");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PopulationFrame ap = new PopulationFrame(Helper.getWidth(),Helper.getHeight());

        f.getContentPane().add(ap);
        f.pack();
        f.setVisible(true);
    }

}
