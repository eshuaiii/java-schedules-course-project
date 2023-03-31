package ui.gui;

import model.course.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates an instance of the frame with the Add Course GUI.
public class AddCourseFrame extends FrameTemplate implements ActionListener {

    JLabel title;
    JTextField courseNameField;
    JTextField courseNumField;
    JTextField courseSectionField;
    JLabel courseNameLabel;
    JLabel courseNumLabel;
    JLabel courseSectionLabel;
    JButton addCourseButton;
    JButton cancelButton;

    // EFFECTS: creates a new AddCourseFrame with all required elements.
    public AddCourseFrame(CourseAppGUI c) {
        super("Add a Course", c);
        this.setLayout(new GridLayout(9, 1, 10, 10));

        createLabels();
        createFields();

        addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(this);

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(this);

        addElements();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the text fields required to add a course
    private void createFields() {
        courseNameField = new JTextField();
        courseNumField = new JTextField();
        courseSectionField = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: adds all the elements to the JFrame
    private void addElements() {
        this.add(title);
        this.add(courseNameLabel);
        this.add(courseNameField);
        this.add(courseNumLabel);
        this.add(courseNumField);
        this.add(courseSectionLabel);
        this.add(courseSectionField);
        this.add(addCourseButton);
        this.add(cancelButton);
    }

    // MODIFIES: this
    // EFFECTS: creates all labels and sets their alignment to centre.
    private void createLabels() {
        title = new JLabel();
        title.setText("Add a new course:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        courseNameLabel = new JLabel();
        courseNameLabel.setText("Course Name:");
        courseNameLabel.setVerticalAlignment(JLabel.CENTER);
        courseNameLabel.setHorizontalAlignment(JLabel.CENTER);
        courseNumLabel = new JLabel();
        courseNumLabel.setText("Course Number:");
        courseNumLabel.setVerticalAlignment(JLabel.CENTER);
        courseNumLabel.setHorizontalAlignment(JLabel.CENTER);
        courseSectionLabel = new JLabel();
        courseSectionLabel.setText("Course Section:");
        courseSectionLabel.setVerticalAlignment(JLabel.CENTER);
        courseSectionLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    // EFFECTS: listens for events from the buttons in the frame.
    //          If addCourseButton is pressed, ensure inputs are valid before passing into addCourse method
    //          If cancelButton is pressed, do nothing and return to HomeFrame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            String courseSection = courseSectionField.getText();
            if (courseNameField.getText().isEmpty() || courseNumField.getText().isEmpty() || courseSection.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty inputs, please!",
                        "Empty inputs!", JOptionPane.WARNING_MESSAGE);
            } else {
                Course course = courseApp.addCourse(courseNameField.getText(), courseNumField.getText(), courseSection);
                if (course == null) {
                    JOptionPane.showMessageDialog(this,
                            "Oops - the course number and section must be an integer!",
                            "Integer Inputs!", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Course added: " + course.courseLong(), "Course Successfully Added!",
                            JOptionPane.INFORMATION_MESSAGE);
                    new HomeFrame(courseApp);
                    this.dispose();
                }
            }
        } else if (e.getSource() == cancelButton) {
            new HomeFrame(courseApp);
            this.dispose();
        }
    }
}