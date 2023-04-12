package ui.gui;

import model.account.Student;
import model.course.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Creates an instance of the frame with the Search Course GUI.
public class SearchResultsFrame extends FrameTemplate implements ActionListener {

    JButton searchCourseStudentButton;
    JButton cancelButton;
    JButton sharedCoursesButton;

    DefaultListModel resultList;
    JList resultJList;

    String op;
    Map<String, String> result;
    Student student;
    JLabel title;
    JScrollPane resultScrollPane;

    // EFFECTS: creates an instance of SearchResultsFrame without a Student parameter (i.e. when looking for a course)
    public SearchResultsFrame(String op, Map<String, String> result, CourseAppGUI c) {
        super("Search Results", c);
        this.op = op;
        this.result = result;
        this.setLayout(new GridLayout(5, 1, 10, 10));

        title = new JLabel();
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        createResultList(result);

        resultJList.setVisibleRowCount(5);
        resultScrollPane = new JScrollPane(resultJList);

        searchCourseStudentButton = new JButton();
        searchCourseStudentButton.addActionListener(this);

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(this);

        // first go grid then go elements?
        this.add(title);
        this.add(resultScrollPane);
        this.add(searchCourseStudentButton);

        operationCheck(op);

        this.add(cancelButton);
        this.setVisible(true);
    }

    // EFFECTS: creates an instance of SearchResultsFrame when looking for a student (e.g. all or shared courses)
    public SearchResultsFrame(String op, Map<String, String> result, Student student, CourseAppGUI c) {
        super("Search Results", c);
        this.op = op;
        this.result = result;
        this.student = student;
        this.setLayout(new GridLayout(5, 1, 10, 10));

        title = new JLabel();
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title);

        createResultList(result);

        resultJList.setVisibleRowCount(5);
        resultScrollPane = new JScrollPane(resultJList);

        searchCourseStudentButton = new JButton();
        searchCourseStudentButton.addActionListener(this);

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(this);

        this.add(resultScrollPane);
        this.add(searchCourseStudentButton);

        operationCheck(op);

        this.add(cancelButton);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: fills in the JList with the courses/students passed in results.
    private void createResultList(Map<String, String> result) {
        // list of courses element
        resultList = new DefaultListModel();
        // placeholder random stuff
        List<String> sortedList = new ArrayList<>(result.keySet());
        Collections.sort(sortedList);
        addAllToList(resultList, sortedList);
        // resultList.addAll(sortedList); <- Java 11
        //Create the list and put it in a scroll pane.
        resultJList = new JList(resultList);
        resultJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // MODIFIES: this
    // EFFECTS: a workaround to .addAll method since Java 8/11 issue...
    private void addAllToList(DefaultListModel dlm, List<String> list) {
        for (String s : list) {
            dlm.addElement(s);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes button and title labels depending on operation
    private void operationCheck(String op) {
        if (op.equals("student")) {
            title.setText("These are the courses " + student.getFirstName() + " has.\n"
                    + "View shared courses by pressing the \"view shared courses\" button.\n"
                    + "Or, view a course's students by pressing the \"view students\" button.");
            searchCourseStudentButton.setText("View Students");
            sharedCoursesButton = new JButton("View Shared Courses");
            sharedCoursesButton.addActionListener(this);
            this.add(sharedCoursesButton);
        } else if (op.equals("course")) {
            title.setText("These are the students in this course.\n"
                    + "View a student's courses by pressing the \"view courses\" button.");
            searchCourseStudentButton.setText("View Courses");
        } else if (op.equals("shared")) {
            title.setText("These are the courses you share with " + student.getFirstName() + ".\n"
                    + "View a course's students by pressing the \"view students\" button.");
            searchCourseStudentButton.setText("View Students");
        }
    }

    // MODIFIES: this
    // EFFECTS: listens to button press events
    //          If searchCourseStudentButton is pressed, either searches for a selected course or that student.
    //          If sharedCourseButton is pressed, searches for courses the two students share
    //          If cancelButton is pressed, goes back to HomeFrame.
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchCourseStudentButton) {
            if (resultJList.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select something!", "Nothing selected",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                String selectedOption = resultJList.getSelectedValue().toString();
                String selectedString = result.get(selectedOption);
                if (op.equals("student") || op.equals("shared")) {
                    Course selectedCourse = courseApp.courseList.getCourseList().get(selectedString);
                    Map<String, String> courseStudents = selectedCourse.getStudentNamesGUI();
                    new SearchResultsFrame("course", courseStudents, courseApp);
                    this.dispose();
                } else if (op.equals("course")) {
                    Student selectedStudent = courseApp.accountList.searchStudent(selectedString);
                    Map<String, String> studentCourses = selectedStudent.getCourseNamesGUI();
                    new SearchResultsFrame("student", studentCourses, selectedStudent, courseApp);
                    this.dispose();
                }
            }

        } else if (e.getSource() == sharedCoursesButton) {
            Map<String, String> sharedCourses = courseApp.currentStudent.getSharedCoursesGUI(student);
            if (sharedCourses == null) {
                JOptionPane.showMessageDialog(this, "You don't share any courses with " + student.getFirstName(),
                        "Student not found", JOptionPane.WARNING_MESSAGE);
            } else {
                new SearchResultsFrame("shared", sharedCourses, student, courseApp);
                this.dispose();
            }

        } else if (e.getSource() == cancelButton) {
            new HomeFrame(courseApp);
            this.dispose();
        }
    }
}