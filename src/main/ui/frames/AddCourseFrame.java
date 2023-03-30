package ui.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseFrame extends FrameTemplate implements ActionListener {

    JTextField courseNameField;
    JTextField courseNumField;
    JTextField courseSectionField;
    JButton addCourseButton;
    JButton cancelButton;

    public AddCourseFrame() {
        super("Add a Course");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(9, 1, 10, 10));
        this.add(panel);

        JLabel title = new JLabel();
        title.setText("Add a new course:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        courseNameField = new JTextField();
        courseNumField = new JTextField();
        courseSectionField = new JTextField();

        JLabel courseNameLabel = new JLabel();
        courseNameLabel.setText("Course Name:");
        courseNameLabel.setVerticalAlignment(JLabel.CENTER);
        courseNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel courseNumField = new JLabel();
        courseNumField.setText("Course Number:");
        courseNumField.setVerticalAlignment(JLabel.CENTER);
        courseNumField.setHorizontalAlignment(JLabel.CENTER);

        JLabel courseSectionField = new JLabel();
        courseSectionField.setText("Course Section:");
        courseSectionField.setVerticalAlignment(JLabel.CENTER);
        courseSectionField.setHorizontalAlignment(JLabel.CENTER);

        addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        // first go grid then go elements?
        panel.add(title);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        panel.add(courseNumField);
        panel.add(this.courseNumField);
        panel.add(courseSectionField);
        panel.add(this.courseSectionField);
        panel.add(addCourseButton);
        panel.add(cancelButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            System.out.println("New Course Added!");
            new HomeFrame();
            this.dispose();
        } else if (e.getSource() == cancelButton) {
            System.out.println("Cancelled!");
            new HomeFrame();
            this.dispose();
        }
    }
}