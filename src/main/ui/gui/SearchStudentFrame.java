package ui.gui;

import model.account.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

// Creates an instance of the frame with the Search Student GUI.
public class SearchStudentFrame extends FrameTemplate implements ActionListener {

    JTextField usernameField;
    JButton searchStudentButton;
    JButton cancelButton;

    // EFFECTS: creates a new SearchStudentFrame with necessary GUI elements
    public SearchStudentFrame(CourseAppGUI c) {
        super("Search for a Student", c);
        this.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel title = new JLabel();
        title.setText("Search for a student by username:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField();

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        searchStudentButton = new JButton("Search Student");
        searchStudentButton.addActionListener(this);

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(this);

        this.add(title);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(searchStudentButton);
        this.add(cancelButton);

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: listens to button press events
    //          If searchStudentButton is pressed, checks for valid inputs before searching for student.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchStudentButton) {
            String username = usernameField.getText();
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty inputs, please!",
                        "Empty inputs!", JOptionPane.WARNING_MESSAGE);
            } else {
                Student student = courseApp.accountList.searchStudent(username);
                if (student == null) {
                    JOptionPane.showMessageDialog(this, "Oops - can't find this student!",
                            "Student not found", JOptionPane.WARNING_MESSAGE);
                } else {
                    Map<String, String> studentCourses = student.getCourseNamesGUI();
                    new SearchResultsFrame("student", studentCourses, student, courseApp);
                    this.dispose();
                }
            }
        } else if (e.getSource() == cancelButton) {
            new HomeFrame(courseApp);
            this.dispose();
        }
    }
}