package ui.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchStudentFrame extends FrameTemplate implements ActionListener {

    JTextField usernameField;
    JButton searchStudentField;
    JButton cancelButton;

    public SearchStudentFrame() {
        super("Search for a Course");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        this.add(panel);

        JLabel title = new JLabel();
        title.setText("Search for a student by username:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField();

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        searchStudentField = new JButton("Search Student");
        searchStudentField.addActionListener(this);

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(this);

        // first go grid then go elements?
        panel.add(title);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(searchStudentField);
        panel.add(cancelButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchStudentField) {
            System.out.println("Trying to search for the student!");
            new SearchResultsFrame("student");
            this.dispose();
        } else if (e.getSource() == cancelButton) {
            System.out.println("Cancelled!");
            new HomeFrame();
            this.dispose();
        }
    }
}