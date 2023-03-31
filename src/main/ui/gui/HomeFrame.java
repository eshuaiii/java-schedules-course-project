package ui.gui;

import model.course.Course;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Creates an instance of the frame with the Home Page GUI.
public class HomeFrame extends FrameTemplate implements ActionListener, ListSelectionListener {

    JLabel title;
    JLabel courseLabel;
    JLabel classmatesLabel;
    JPanel panelLeft;
    JPanel panelRight;
    JPanel panelCenter;
    JButton addCourseButton;
    JButton removeCourseButton;
    JButton searchCourseButton;
    JButton searchStudentButton;
    JButton saveButton;
    JButton signOutButton;

    DefaultListModel courseList;
    JList courseJList;
    JScrollPane listCourseScrollPane;
    DefaultListModel studentList;
    JList studentJList;
    JScrollPane listStudentScrollPane;

    // EFFECTS: creates a new HomeFrame with all required elements.
    public HomeFrame(CourseAppGUI c) {
        super("Main Menu", c);
        this.setLayout(new BorderLayout());

        createPanels();
        createLabels();
        createLists();

        // create buttons
        searchCourseButton = new JButton("Search for a course");
        searchStudentButton = new JButton("Search for a student");
        searchCourseButton.addActionListener(this);
        searchStudentButton.addActionListener(this);
        addCourseButton = new JButton("Add a course");
        removeCourseButton = new JButton("Remove a course");
        addCourseButton.addActionListener(this);
        removeCourseButton.addActionListener(this);
        removeCourseButton.setEnabled(false);
        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(this);
        saveButton = new JButton("Save to File");
        saveButton.addActionListener(this);

        addElements();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the list elements involved in the GUI.
    private void createLists() {
        // courses list
        courseList = new DefaultListModel();
        List<String> studentCourses = new ArrayList<>(courseApp.currentStudent.getCourseNamesGUI().keySet());
        Collections.sort(studentCourses);
        courseList.addAll(studentCourses);
        //Create the list and put it in a scroll pane.
        courseJList = new JList(courseList);
        courseJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseJList.addListSelectionListener(this);
        courseJList.setVisibleRowCount(5);
        listCourseScrollPane = new JScrollPane(courseJList);

        // student list
        studentList = new DefaultListModel();
        studentJList = new JList(studentList);
        studentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentJList.addListSelectionListener(this);
        studentJList.setVisibleRowCount(5);
        listStudentScrollPane = new JScrollPane(studentJList);
    }

    // MODIFIES: this
    // EFFECTS: creates the labels in the GUI.
    private void createLabels() {
        title = new JLabel();
        title.setText("Hi, " + courseApp.currentStudent.getFirstName() + "! View your courses here.");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        courseLabel = new JLabel();
        courseLabel.setText("Your Courses:");

        classmatesLabel = new JLabel();
        classmatesLabel.setText("Your classmates:");
    }

    // MODIFIES: this
    // EFFECTS: adds the elements to the corresponding GUI panels
    private void addElements() {
        this.add(title, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        panelLeft.add(courseLabel);
        panelLeft.add(listCourseScrollPane);
        panelLeft.add(addCourseButton);
        panelLeft.add(removeCourseButton);
        panelLeft.add(signOutButton);
        panelRight.add(classmatesLabel);
        panelRight.add(listStudentScrollPane);
        panelRight.add(searchCourseButton);
        panelRight.add(searchStudentButton);
        panelRight.add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: creates the panels involved in the GUI frame
    private void createPanels() {
        panelLeft = new JPanel();
        panelRight = new JPanel();
        panelCenter = new JPanel();
        panelLeft.setLayout(new GridLayout(5, 1, 10, 10));
        panelRight.setLayout(new GridLayout(5, 1, 10, 10));
        panelCenter.setLayout(new GridLayout(1, 2, 10, 10));
        panelCenter.add(panelLeft);
        panelCenter.add(panelRight);
    }

    // MODIFIES: this, courseApp
    // EFFECTS: listens to events from buttons
    //          If addCourseButton is pressed, dispose this frame and create a new AddCourseFrame
    //          If removeCourseButton is pressed (REQUIRES: a course selected), prompt user for deletion.
    //          If searchCourseButton is pressed, dispose this frame and create a SearchCourseFrame
    //          If searchStudentButton is pressed, dispose this frame and create a SearchStudentFrame
    //          If saveButton is pressed, save the session
    //          If signOutButton is pressed, log user out, dispose this frame and create a PreLoginFrame
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            new AddCourseFrame(courseApp);
            this.dispose();
        } else if (e.getSource() == removeCourseButton) {
            Object[] options = {"Remove course", "Cancel"};
            String currentCourse = courseJList.getSelectedValue().toString();
            String currentCourseID = courseApp.currentStudent.getCourseNamesGUI().get(currentCourse);
            int n = JOptionPane.showOptionDialog(this,
                    "You selected: " + currentCourse + ". Would you like to remove it?","Remove a Course",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
            if (n == JOptionPane.YES_OPTION) {
                courseApp.removeCourse(currentCourseID);
                JOptionPane.showMessageDialog(this,"Course removed: " + currentCourse,
                        "Course Successfully Removed!", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new HomeFrame(courseApp);
            }
        } else if (e.getSource() == searchCourseButton) {
            new SearchCourseFrame(courseApp);
            this.dispose();
        } else if (e.getSource() == searchStudentButton) {
            new SearchStudentFrame(courseApp);
            this.dispose();
        } else if (e.getSource() == saveButton) {
            courseApp.saveSession();
            JOptionPane.showMessageDialog(this, "Data Saved!","Save Session", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == signOutButton) {
            courseApp.signOut();
            new PreLoginFrame(courseApp);
            this.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: listens to when courseJList is changed: if no course is selected disable removeCourseButton, else enable
    //          when selection changes, change studentList to update with the students in that course.
    @Override
    // implemented from java ListDemoProject, https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
    public void valueChanged(ListSelectionEvent e) {
        // TODO: refactor with GUI class
        if (!e.getValueIsAdjusting()) {
            if (e.getSource() == courseJList) {
                if (courseJList.isSelectionEmpty()) {
                    removeCourseButton.setEnabled(false);
                } else {
                    removeCourseButton.setEnabled(true);
                    String courseKey = courseApp.currentStudent.getCourseNamesGUI().get(courseJList.getSelectedValue());
                    Course course = courseApp.courseList.getCourseList().get(courseKey);
                    List<String> courseStudents = course.getStudentListSorted();
                    studentList.removeAllElements();
                    studentList.addAll(courseStudents);
                }
                System.out.println(courseJList.getSelectedValue());
            }
        }
    }
}