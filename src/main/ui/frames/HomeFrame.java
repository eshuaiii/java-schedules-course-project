package ui.frames;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HomeFrame extends FrameTemplate implements ActionListener, ListSelectionListener {

    JButton addCourseButton;
    JButton removeCourseButton;
    JButton searchCourseButton;
    JButton searchStudentButton;
    JButton saveButton;

    DefaultListModel courseList;
    JList courseJList;
    DefaultListModel studentList;
    JList studentJList;

    public HomeFrame() {
        super("Main Menu");
        JPanel panelBottom = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();
        this.setLayout(new GridLayout(2, 1));

        panelBottom.setBackground(new Color(0xfafafa));
        panelBottom.setLayout(new GridLayout(1, 2, 10, 10));
        panelLeft.setBackground(new Color(0xfafafa));
        panelLeft.setLayout(new GridLayout(5, 1, 10, 10));
        panelRight.setBackground(new Color(0xfafafa));
        panelRight.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel title = new JLabel();
        title.setText("Hi, Eric! Here are your courses:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title);
        this.add(panelBottom);

        panelBottom.add(panelLeft);
        panelBottom.add(panelRight);


        // list of courses element
        courseList = new DefaultListModel();
        // placeholder random stuff
        courseList.addAll(List.of(new String[]{"Hi", "Bye", "A", "A", "A", "A", "a", "A", "A", "A"}));
        //Create the list and put it in a scroll pane.
        courseJList = new JList(courseList);
        courseJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        courseJList.addListSelectionListener(this);
        courseJList.setVisibleRowCount(5);
        JScrollPane listCourseScrollPane = new JScrollPane(courseJList);



        JLabel placeholder1 = new JLabel();
        placeholder1.setText("Your Courses:");
        addCourseButton = new JButton("Add a course");
        removeCourseButton = new JButton("Remove a course");
        addCourseButton.addActionListener(this);
        removeCourseButton.addActionListener(this);

        // student list
        studentList = new DefaultListModel();
        // placeholder random stuff
        studentList.addAll(List.of(new String[]{"Hi", "Bye"}));
        //Create the list and put it in a scroll pane.
        studentJList = new JList(studentList);
        studentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        studentJList.addListSelectionListener(this);
        studentJList.setVisibleRowCount(5);
        JScrollPane listStudentScrollPane = new JScrollPane(studentJList);


        JLabel placeholder2 = new JLabel();
        placeholder2.setText("Your classmates:");
        searchCourseButton = new JButton("Search for a course");
        searchStudentButton = new JButton("Remove a course");
        searchCourseButton.addActionListener(this);
        searchStudentButton.addActionListener(this);

        saveButton = new JButton("Save to File");
        saveButton.addActionListener(this);

        // first go grid then go elements?
        panelLeft.add(placeholder1);
        panelLeft.add(listCourseScrollPane);
        panelLeft.add(addCourseButton);
        panelLeft.add(removeCourseButton);
        panelRight.add(placeholder2);
        panelRight.add(listStudentScrollPane);
        panelRight.add(searchCourseButton);
        panelRight.add(searchStudentButton);
        panelRight.add(saveButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            System.out.println("Adding a course!");
            new AddCourseFrame();
            this.dispose();
        } else if (e.getSource() == removeCourseButton) {
            System.out.println("You really sure you want to remove that?");
        } else if (e.getSource() == searchCourseButton) {
            System.out.println("Let's search for that course!");
        } else if (e.getSource() == searchStudentButton) {
            System.out.println("Finding a student!");
        } else if (e.getSource() == saveButton) {
            System.out.println("Data Saved!");
        }
    }

    @Override
    // implemented from java ListDemoProject,
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (e.getSource() == courseJList) {
                System.out.println(courseJList.getSelectedValue());
            }
        }
    }
}