package ui.gui;

import javax.swing.*;
import java.awt.*;

// A template with primary components of every frame of the GUI.
public class FrameTemplate extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    CourseAppGUI courseApp;

    // EFFECTS: constructor for a default frame design
    public FrameTemplate(String name, CourseAppGUI c) {
        this.courseApp = c;
        this.setTitle("Schedules: " + name);
        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setBackground(new Color(0xffffff));
        centreOnScreen();
        ImageIcon image = new ImageIcon("data/calendar.png");
        this.setIconImage(image.getImage());
        // ImageIcon changes the icon; ImageIcon image = new ImageIcon(path), frame.setIconImage(image.getImage());
    }

    // MODIFIES: this
    // EFFECTS: helper to centre main application on desktop
    // Referenced from AlarmSystem, https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

}
