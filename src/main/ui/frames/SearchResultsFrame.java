package ui.frames;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchResultsFrame extends FrameTemplate implements ActionListener, ListSelectionListener {

    JButton searchCourseStudentButton;
    JButton cancelButton;
    JButton sharedCoursesButton;

    DefaultListModel resultList;
    JList resultJList;

    String op;

    public SearchResultsFrame(String op) {
        super("Search Results");
        this.op = op;
        JPanel panel = new JPanel();

        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        this.add(panel);

        JLabel title = new JLabel();
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title);

        // list of courses element
        resultList = new DefaultListModel();
        // placeholder random stuff
        resultList.addAll(List.of(new String[]{"Hi", "Bye", "A", "A", "A", "A", "a", "A", "A", "A"}));
        //Create the list and put it in a scroll pane.
        resultJList = new JList(resultList);
        resultJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        resultJList.addListSelectionListener(this);
        resultJList.setVisibleRowCount(5);
        JScrollPane resultScrollPane = new JScrollPane(resultJList);

        searchCourseStudentButton = new JButton();
        searchCourseStudentButton.addActionListener(this);

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(this);

        // first go grid then go elements?
        panel.add(resultScrollPane);
        panel.add(searchCourseStudentButton);

        if (op == "student") {
            title.setText("These are the courses this student has.\n"
                    + "View shared courses by pressing the \"view shared courses\" button.\n"
                    + "Or, view a course's students by pressing the \"view students\" button.");
            searchCourseStudentButton.setText("View Students");
            sharedCoursesButton = new JButton("View Shared Courses");
            sharedCoursesButton.addActionListener(this);
            panel.add(sharedCoursesButton);
        } else if (op == "course") {
            title.setText("These are the students in this course.\n"
                    + "View a student's courses by pressing the \"view courses\" button.");
            searchCourseStudentButton.setText("View Courses");
        } else if (op == "shared") {
            title.setText("These are the courses you share with the student.\n"
                    + "View a course's students by pressing the \"view students\" button.");
            searchCourseStudentButton.setText("View Students");
        }
        panel.add(cancelButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchCourseStudentButton) {
            if (op == "student" || op == "shared") {
                System.out.println("Finding students in a course!");
                new SearchResultsFrame("course");
                this.dispose();
            } else if (op == "course") {
                System.out.println("Find the courses a student has!");
                new SearchResultsFrame("student");
                this.dispose();
            }
        } else if (e.getSource() == sharedCoursesButton) {
            System.out.println("Finding shared courses!");
            new SearchResultsFrame("shared");
            this.dispose();
        } else if (e.getSource() == cancelButton) {
            System.out.println("Going back home!");
            new HomeFrame();
            this.dispose();
        }
    }

    @Override
    // implemented from java ListDemoProject,
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (e.getSource() == resultJList) {
                System.out.println(resultJList.getSelectedValue());
            }
        }
    }
}