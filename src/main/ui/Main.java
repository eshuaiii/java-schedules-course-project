package ui;

import ui.frames.PreLoginFrame;

import java.io.FileNotFoundException;

// Starts the program by creating a new CourseApp.
public class Main {
    public static void main(String[] args) {

        try {
            new PreLoginFrame();
            new CourseApp();
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Unable to run application: file not found.");
        }
    }
}
