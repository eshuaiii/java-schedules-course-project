package ui.gui;

import model.course.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

// Creates an instance of the frame with the Search Course GUI.
public class SearchCourseFrame extends FrameTemplate implements ActionListener {

    JLabel title;
    JLabel courseNameLabel;
    JLabel courseNumLabel;
    JLabel courseSectionLabel;
    JTextField courseNameField;
    JTextField courseNumField;
    JTextField courseSectionField;
    JButton searchCourseButton;
    JButton cancelButton;

    // EFFECTS: creates a new instance of SearchCourseFrame with necessary GUI elements.
    public SearchCourseFrame(CourseAppGUI c) {
        super("Search for a Course", c);
        this.setLayout(new GridLayout(9, 1, 10, 10));

        createLabels();

        courseNameField = new JTextField();
        courseNumField = new JTextField();
        courseSectionField = new JTextField();

        searchCourseButton = new JButton("Search Course");
        searchCourseButton.addActionListener(this);

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(this);

        addElements();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds elements to the JFrame
    private void addElements() {
        this.add(title);
        this.add(courseNameLabel);
        this.add(courseNameField);
        this.add(courseNumLabel);
        this.add(courseNumField);
        this.add(courseSectionLabel);
        this.add(courseSectionField);
        this.add(searchCourseButton);
        this.add(cancelButton);
    }

    // MODIFIES: this
    // EFFECTS: creates the labels
    private void createLabels() {
        title = new JLabel();
        title.setText("Search for a course:");
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


    // MODIFIES: this
    // EFFECTS: listens to button events
    //          If searchCourseButton, checks that inputs are valid before going to SearchResultsFrame
    //          If cancelButton, goes back to HomeFrame.
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchCourseButton) {
            String courseSection = courseSectionField.getText();
            if (courseNameField.getText().isEmpty() || courseNumField.getText().isEmpty() || courseSection.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty inputs, please!",
                        "Empty inputs!", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    Course course = courseApp.searchCourse(courseNameField.getText(), courseNumField.getText(),
                            courseSection);
                    if (course == null) {
                        JOptionPane.showMessageDialog(this, "Oops - this course doesn't exist! Try again.",
                                "Course not found", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Map<String, String> studentMap = course.getStudentNamesGUI();
                        new SearchResultsFrame("course", studentMap, courseApp);
                        this.dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,"Oops - the course number and section must be an integer!",
                            "Integer Inputs!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else if (e.getSource() == cancelButton) {
            new HomeFrame(courseApp);
            this.dispose();
        }
    }
}