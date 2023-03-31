package ui;

import ui.gui.CourseAppGUI;

import java.io.FileNotFoundException;

// Starts the program by creating a new CourseApp.
public class Main {
    public static void main(String[] args) {

        try {
            new CourseAppGUI();
//            new CourseApp();
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Unable to run application: file not found.");
        }
    }
}
