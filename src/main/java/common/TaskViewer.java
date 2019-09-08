package common;

import javax.swing.*;
import java.awt.*;

public class TaskViewer {

    public TaskViewer(JPanel panel){
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(panel);
    }


    //METHODS
    public static void init(JPanel panel) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignore) {}

        JFrame frame = new JFrame("TaskViewer");
        frame.setContentPane(new TaskViewer(panel).rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
       // frame.setResizable(false);
        frame.pack();

        frame.setVisible(true);
    }

    //FIELDS
    private JPanel rootPanel;
}
