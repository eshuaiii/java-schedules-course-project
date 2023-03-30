package ui;

import model.account.AllAccounts;
import model.account.Student;
import model.course.AllCourses;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.frames.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Creates a new course app instance.
public class CourseAppGUI {
    private static final String JSON_STORE = "./data/session.json";
    private AllAccounts accountList;
    private AllCourses courseList;
    private Student currentStudent;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame preLoginFrame;
    private JFrame loginFrame;
    private JFrame signupFrame;
    private JFrame homeFrame;
    private JFrame addCourseFrame;
    private JFrame searchCourseFrame;
    private JFrame searchStudentFrame;
    private JFrame searchResultsFrame;

    // EFFECTS: creates a new CourseApp and initializes fields + Scanner, then begins at preLoggedInScreen.
    public CourseAppGUI() throws FileNotFoundException {
        currentStudent = null;
        accountList = new AllAccounts();
        courseList = new AllCourses();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        preLoginFrame();
    }

    private void preLoginFrame() {
        JButton loginButton;
        JButton signUpButton;
        JButton loadButton;
        preLoginFrame = new FrameTemplate("Welcome!");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        preLoginFrame.add(panel);

        JLabel title = new JLabel();
        title.setText("Hi!");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        loginButton = new JButton("Log In");
        loginButton.addActionListener(new PreLoginButtonActionListener());
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new PreSignupButtonActionListener());

        // temporary button to load from file -> menu item?
        loadButton = new JButton("Load from File");
        loadButton.addActionListener(new PreLoadButtonActionListener());

        // first go grid then go elements?
        panel.add(title);
        panel.add(loginButton);
        panel.add(signUpButton);
        panel.add(loadButton);

        preLoginFrame.setVisible(true);
    }

    private class PreLoginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Opening Login!");
            loginFrame();
            preLoginFrame.dispose();
        }
    }

    private class PreSignupButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Opening Signup!");
            signupFrame();
            preLoginFrame.dispose();
        }
    }

    private class PreLoadButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Loading Data!");
            loadSession();
        }
    }

    private void loginFrame() {
        JTextField usernameField;
        JPasswordField passwordField;
        JButton loginButton;

        loginFrame = new FrameTemplate("Log in");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        loginFrame.add(panel);

        JLabel title = new JLabel();
        title.setText("Login:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        loginButton = new JButton("Log In");
        loginButton.addActionListener(new LoginButtonActionListener());

        // first go grid then go elements?
        panel.add(title);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        loginFrame.setVisible(true);
    }

    private class LoginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // do checks!
            System.out.println("Logged In!");
            homeFrame();
            loginFrame.dispose();
        }
    }

    private void signupFrame() {
        JTextField usernameField;
        JTextField firstNameField;
        JTextField lastNameField;
        JPasswordField passwordField;
        JButton signupButton;

        signupFrame = new FrameTemplate("Sign up");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(10, 1, 10, 10));
        signupFrame.add(panel);

        JLabel title = new JLabel();
        title.setText("Signup:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        passwordField = new JPasswordField();

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name:");
        firstNameLabel.setVerticalAlignment(JLabel.CENTER);
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name:");
        lastNameLabel.setVerticalAlignment(JLabel.CENTER);
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new SignupButtonActionListener());

        // first go grid then go elements?
        panel.add(title);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(signupButton);

        signupFrame.setVisible(true);
    }

    private class SignupButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // do checks!
            System.out.println("Signed Up!");
            homeFrame();
            signupFrame.dispose();
        }
    }

    private void homeFrame() {
        JButton addCourseButton;
        JButton removeCourseButton;
        JButton searchCourseButton;
        JButton searchStudentButton;
        JButton saveButton;
        JButton signOutButton;

        DefaultListModel courseList;
        JList courseJList;
        DefaultListModel studentList;
        JList studentJList;

        homeFrame = new FrameTemplate("Main Menu");
        JPanel panelBottom = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();
        homeFrame.setLayout(new GridLayout(2, 1));

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
        homeFrame.add(title);
        homeFrame.add(panelBottom);

        panelBottom.add(panelLeft);
        panelBottom.add(panelRight);


        // list of courses element
        courseList = new DefaultListModel();
        // placeholder random stuff
        courseList.addAll(List.of(new String[]{"Hi", "Bye", "A", "A", "A", "A", "a", "A", "A", "A"}));
        //Create the list and put it in a scroll pane.
        courseJList = new JList(courseList);
        courseJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        courseJList.addListSelectionListener(new CourseListChangedActionListener(courseJList));
        courseJList.setVisibleRowCount(5);
        JScrollPane listCourseScrollPane = new JScrollPane(courseJList);



        JLabel placeholder1 = new JLabel();
        placeholder1.setText("Your Courses:");
        addCourseButton = new JButton("Add a course");
        removeCourseButton = new JButton("Remove a course");
        addCourseButton.addActionListener(new HomeAddCourseActionListener());
        removeCourseButton.addActionListener(new RemoveCourseActionListener());

        // student list
        studentList = new DefaultListModel();
        // placeholder random stuff
        studentList.addAll(List.of(new String[]{"Hi", "Bye"}));
        //Create the list and put it in a scroll pane.
        studentJList = new JList(studentList);
        studentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        studentJList.addListSelectionListener(new StudentListChangedActionListener(studentJList));
        studentJList.setVisibleRowCount(5);
        JScrollPane listStudentScrollPane = new JScrollPane(studentJList);


        JLabel placeholder2 = new JLabel();
        placeholder2.setText("Your classmates:");
        searchCourseButton = new JButton("Search for a course");
        searchStudentButton = new JButton("Search for a student");
        searchCourseButton.addActionListener(new HomeSearchCourseActionListener());
        searchStudentButton.addActionListener(new HomeSearchStudentActionListener());

        saveButton = new JButton("Save to File");
        saveButton.addActionListener(new SaveActionListener());
        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new SignOutActionListener());

        // first go grid then go elements?
        panelLeft.add(placeholder1);
        panelLeft.add(listCourseScrollPane);
        panelLeft.add(addCourseButton);
        panelLeft.add(removeCourseButton);
        panelLeft.add(signOutButton);
        panelRight.add(placeholder2);
        panelRight.add(listStudentScrollPane);
        panelRight.add(searchCourseButton);
        panelRight.add(searchStudentButton);
        panelRight.add(saveButton);

        homeFrame.setVisible(true);
    }

    private class HomeAddCourseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // do checks!
            System.out.println("Adding a course!");
            addCourseFrame();
            homeFrame.dispose();
        }
    }

    private class RemoveCourseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("You really sure you want to remove that?");
            Object[] options = {"Remove course", "Cancel"};
            int n = JOptionPane.showOptionDialog(homeFrame,
                    "You selected: a course. Would you like to remove it?",
                    "Remove a Course",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (n == JOptionPane.YES_OPTION) {
                System.out.println("Course removed!");
            } else if (n == JOptionPane.NO_OPTION) {
                System.out.println("No removing today!");
            }
        }
    }

    private class HomeSearchCourseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // do checks!
            System.out.println("Let's search for that course!");
            searchCourseFrame();
            homeFrame.dispose();
        }
    }

    private class HomeSearchStudentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // do checks!
            System.out.println("Finding a student!");
            searchStudentFrame();
            homeFrame.dispose();
        }
    }

    private class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveSession();
            System.out.println("Data Saved!");
        }
    }

    private class SignOutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            signOut();
            System.out.println("Signed Out!");
            preLoginFrame()
        }
    }

    private class CourseListChangedActionListener implements ListSelectionListener {
        JList courseJList;

        public CourseListChangedActionListener(JList courseJList) {
            this.courseJList = courseJList;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                System.out.println(courseJList.getSelectedValue());
            }
        }
    }

    private class StudentListChangedActionListener implements ListSelectionListener {
        JList studentJList;

        public StudentListChangedActionListener(JList studentJList) {
            this.studentJList = studentJList;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                System.out.println(studentJList.getSelectedValue());
            }
        }
    }

    private void addCourseFrame() {
        JTextField courseNameField;
        JTextField courseNumField;
        JTextField courseSectionField;
        JButton addCourseButton;
        JButton cancelButton;

        addCourseFrame = new FrameTemplate("Add a Course");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(9, 1, 10, 10));
        addCourseFrame.add(panel);

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

        JLabel courseNumLabel = new JLabel();
        courseNumLabel.setText("Course Number:");
        courseNumLabel.setVerticalAlignment(JLabel.CENTER);
        courseNumLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel courseSectionLabel = new JLabel();
        courseSectionLabel.setText("Course Section:");
        courseSectionLabel.setVerticalAlignment(JLabel.CENTER);
        courseSectionLabel.setHorizontalAlignment(JLabel.CENTER);

        addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(new AddCourseActionListener());

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(new GoHomeActionListener());

        // first go grid then go elements?
        panel.add(title);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        panel.add(courseNumLabel);
        panel.add(courseNumField);
        panel.add(courseSectionLabel);
        panel.add(courseSectionField);
        panel.add(addCourseButton);
        panel.add(cancelButton);

        addCourseFrame.setVisible(true);
    }

    private class AddCourseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // do checks!
            System.out.println("New Course Added!");
            homeFrame();
            addCourseFrame.dispose();
        }
    }

    private class GoHomeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancelled!");
            homeFrame();
            addCourseFrame.dispose();
        }
    }

    private void searchCourseFrame() {
        JTextField courseNameField;
        JTextField courseNumField;
        JTextField courseSectionField;
        JButton searchCourseField;
        JButton cancelButton;

        searchCourseFrame = new FrameTemplate("Search for a Course");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(9, 1, 10, 10));
        searchCourseFrame.add(panel);

        JLabel title = new JLabel();
        title.setText("Search for a course:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        courseNameField = new JTextField();
        courseNumField = new JTextField();
        courseSectionField = new JTextField();

        JLabel courseNameLabel = new JLabel();
        courseNameLabel.setText("Course Name:");
        courseNameLabel.setVerticalAlignment(JLabel.CENTER);
        courseNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel courseNumLabel = new JLabel();
        courseNumLabel.setText("Course Number:");
        courseNumLabel.setVerticalAlignment(JLabel.CENTER);
        courseNumLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel courseSectionLabel = new JLabel();
        courseSectionLabel.setText("Course Section:");
        courseSectionLabel.setVerticalAlignment(JLabel.CENTER);
        courseSectionLabel.setHorizontalAlignment(JLabel.CENTER);

        searchCourseField = new JButton("Search Course");
        searchCourseField.addActionListener(new SearchCourseActionListener());

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(new GoHomeActionListener());

        // first go grid then go elements?
        panel.add(title);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        panel.add(courseNumLabel);
        panel.add(courseNumField);
        panel.add(courseSectionLabel);
        panel.add(courseSectionField);
        panel.add(searchCourseField);
        panel.add(cancelButton);

        searchCourseFrame.setVisible(true);
    }

    private class SearchCourseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Trying to search for the course!");
            searchResultsFrame("course");
            searchCourseFrame.dispose();
        }
    }

    private void searchStudentFrame() {
        JTextField usernameField;
        JButton searchStudentField;
        JButton cancelButton;

        searchStudentFrame = new FrameTemplate("Search for a Course");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        searchStudentFrame.add(panel);

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
        searchStudentField.addActionListener(new SearchStudentActionListener());

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(new GoHomeActionListener());

        // first go grid then go elements?
        panel.add(title);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(searchStudentField);
        panel.add(cancelButton);

        searchStudentFrame.setVisible(true);
    }

    private class SearchStudentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Trying to search for the student!");
            searchResultsFrame("student");
            searchCourseFrame.dispose();
        }
    }

    private void searchResultsFrame(String op) {
        JButton searchCourseStudentButton;
        JButton cancelButton;
        JButton sharedCoursesButton;

        DefaultListModel resultList;
        JList resultJList;

        searchResultsFrame = new FrameTemplate("Search Results");
        JPanel panel = new JPanel();

        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        searchResultsFrame.add(panel);

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

        resultJList.addListSelectionListener(new ResultListChangedActionListener(resultJList));
        resultJList.setVisibleRowCount(5);
        JScrollPane resultScrollPane = new JScrollPane(resultJList);

        searchCourseStudentButton = new JButton();
        searchCourseStudentButton.addActionListener(new SearchCourseStudentActionListener(op));

        cancelButton = new JButton("Go Back Home");
        cancelButton.addActionListener(new GoHomeActionListener());

        // first go grid then go elements?
        panel.add(resultScrollPane);
        panel.add(searchCourseStudentButton);

        if (op == "student") {
            title.setText("These are the courses this student has.\n"
                    + "View shared courses by pressing the \"view shared courses\" button.\n"
                    + "Or, view a course's students by pressing the \"view students\" button.");
            searchCourseStudentButton.setText("View Students");
            sharedCoursesButton = new JButton("View Shared Courses");
            sharedCoursesButton.addActionListener(new SharedCoursesActionListener());
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

        searchResultsFrame.setVisible(true);
    }

    private class SearchCourseStudentActionListener implements ActionListener {
        String op;

        public SearchCourseStudentActionListener(String op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (op == "student" || op == "shared") {
                System.out.println("Finding students in a course!");
                searchResultsFrame.dispose();
                searchResultsFrame("course");
            } else if (op == "course") {
                System.out.println("Find the courses a student has!");
                searchResultsFrame.dispose();
                searchResultsFrame("student");
            }
        }
    }

    private class SharedCoursesActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Finding shared courses!");
            searchResultsFrame.dispose();
            searchResultsFrame("shared");
        }
    }


    private class ResultListChangedActionListener implements ListSelectionListener {
        JList resultJList;

        public ResultListChangedActionListener(JList resultJList) {
            this.resultJList = resultJList;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                System.out.println(resultJList.getSelectedValue());
            }
        }
    }




    // MODIFIES: this
    // EFFECTS: loads a previous session from JSON file.
    // modelled based on the JsonSerializationDemo file,
    //  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void loadSession() {
        List<Object> jsonData;
        try {
            jsonData = jsonReader.read();
            accountList = (AllAccounts) jsonData.get(0);
            courseList = (AllCourses) jsonData.get(1);
            System.out.println("✅ Successfully loaded the data from" + JSON_STORE + " !");
        } catch (IOException e) {
            System.out.println("⚠️ Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: takes in user inputs for signup workflow.
    //          If no incorrect inputs, creates a new student and adds to accountList.
    private void signup(String username, String firstName, String lastName, String password) {
        // TODO: figure out how to capitalize names! How to import apache?
        // TODO: possible refactor since username check already exists in AllAccounts? -> would go over 25
        // TODO: allow spaces in username?
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
            System.out.println("\n⚠️ Oops - no empty inputs please!");
            failedSignUpLogIn("s");
        } else if (accountList.getAccountList().get(username) != null) {
            System.out.println("\n⚠️ Oops - seems like that username is taken.");
            failedSignUpLogIn("s");
        } else {
            currentStudent = accountList.signup(username, firstName, lastName, password);
            System.out.println("\n✅ Signup successful!");
            mainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: Takes user inputs and attempts to log user in. If successful, user's Student is assigned and
    //          user is redirected to main menu.
    private void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("\n⚠️ Oops - no empty inputs please!");
            failedSignUpLogIn("l");
        } else {
            currentStudent = accountList.login(username, password);
            if (currentStudent == null) {
                System.out.println("\n⚠️ Login was not successful.");
                failedSignUpLogIn("l");
            } else {
                System.out.println("\n✅ Login successful!");
                mainMenu();
            }
        }
    }

    // MODIFIES: this, currentStudent, course
    // EFFECTS: adds a course to the currentStudent.
    private void addCourse() {
        System.out.println("\n\033[3m📍 Main Menu -> Add Course\033[0m");
        List<String> courseEntry = courseEntry();
        String courseName = courseEntry.get(0);
        Integer courseNum = -1;
        Integer courseSection = -1;

        try {
            courseNum = Integer.parseInt(courseEntry.get(1));
            courseSection = Integer.parseInt(courseEntry.get(2));
        } catch (NumberFormatException e) {
            System.out.println("Oops - the course number and section must be an integer!");
            failedMain("a");
        }
        // TODO: find a way to break after exception OR refactor this to run with try-catch above
        // Consider checking for emptiness first, then attempt try-catch.
        if (courseName.isEmpty() || courseNum.equals(-1) || courseSection.equals(-1)) {
            System.out.println("\n⚠️ Oops - no empty inputs please!");
            failedMain("a");
        } else {
            Course course = courseList.addCourse(courseName, courseNum, courseSection);
            currentStudent.addCourse(course);
            Course checkCourse = currentStudent.getCourseList().get(course.courseToKey());
            System.out.println("\n✅ Course added: " + checkCourse.courseLong());
            System.out.println("Press 'enter' to return to the main menu...");
            input.next();
        }
    }

    // MODIFIES: this, currentStudent, course
    // EFFECTS: removes a course from the currentStudent
    private void removeCourse() {
        List<Course> courseList = currentStudent.getCourses();
        System.out.println("\n\033[3m📍 Main Menu -> Remove Course\033[0m");

        if (courseList.isEmpty()) {
            System.out.println("\n⚠️ You are not enrolled in any courses!");
        } else {
            System.out.println("\n📝 Please select the course you wish to remove:");
            viewCoursesInternal(courseList);
            try {
                int command = input.nextInt();
                Course removedCourse = courseList.get(command - 1);
                currentStudent.removeCourse(removedCourse);
                this.courseList.checkForEmptyCourse(removedCourse);
                System.out.println("\n✅ Course removed!");
                System.out.println("Press 'enter' to return to the main menu...");
                input.next();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Oops - that's not a valid selection!");
                failedMain("r");
            } catch (InputMismatchException e) {
                // TODO: weird issue about double printing?
                // Try catching mismatch before OutOfBounds; or try casting command from str to int instead
                System.out.println("Oops - your selection must be an integer!");
                failedMain("r");
            }
        }
    }

    // EFFECTS: prints out the courses the user is enrolled in
    private void viewCourses() {
        List<Course> courseList = currentStudent.getCourses();
        System.out.println("\n\033[3m📍 Main Menu -> View Courses\033[0m");

        if (courseList.isEmpty()) {
            System.out.println("\n⚠️ You are not enrolled in any courses!");
            System.out.println("Press 'enter' to return to the main menu...");
            input.next();
        } else {
            // TODO: fix this "any number" part to be any input.
            System.out.println("\n📝 Here are your courses! Select a course to view your classmates, "
                                 + "or any other number to go back to the main menu.");
            viewCoursesInternal(courseList);
            try {
                int command = input.nextInt();
                Course viewCourse = courseList.get(command - 1);
                viewClassmates(viewCourse);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Returning to the Main Menu.");
            } catch (InputMismatchException e) {
                // TODO: weird issue about double printing? Might be because of nextInt?
                // Try catching mismatch before OutOfBounds; or try casting command from str to int instead
                System.out.println("Oops - your selection must be an integer!");
                failedMain("v");
            }
        }
    }

    // EFFECTS: shows all the students enrolled in a given course
    private void viewClassmates(Course course) {
        System.out.println("\n\033[3m📍 Main Menu -> View Courses -> View Students in Course\033[0m");
        System.out.println("Students in " + course.courseLong());
        for (String s : course.getStudentListSorted()) {
            System.out.println("\t" + s);
        }
        System.out.println("Press 'enter' to return to the main menu...");
        input.next();
    }

    // EFFECTS: searches for a course given user inputs
    // TODO: try to get rid of this methodlength warning by combining if/else and making it less efficient :D
    //       declare first, then assign in if/else statement
    @SuppressWarnings("methodlength")
    private void searchCourse() {
        System.out.println("\n\033[3m📍 Main Menu -> Search for a Course\033[0m");
        List<String> courseEntry = courseEntry();
        String courseName = courseEntry.get(0);
        Integer courseNum = -1;
        Integer courseSection = -1;

        try {
            courseNum = Integer.parseInt(courseEntry.get(1));
            courseSection = Integer.parseInt(courseEntry.get(2));
        } catch (NumberFormatException e) {
            System.out.println("Oops - the course number and section must be an integer!");
            failedMain("c");
        }
        // TODO: weird double thing here too
        // Consider checking for emptiness first, then attempt try-catch.
        if (courseName.isEmpty() || courseNum.equals(-1) || courseSection.equals(-1)) {
            System.out.println("\n⚠️ Oops - no empty inputs please!");
            failedMain("c");
        } else {
            Course course = courseList.searchCourse(courseName, courseNum, courseSection);
            if (course == null) {
                System.out.println("\n⚠️ Course not found!");
                failedMain("c");
            } else {
                // TODO: fix breadcrumbs here
                viewClassmates(course);
            }

        }
    }

    // EFFECTS: searches for a student's courses given inputs
    private void searchStudent() {
        System.out.println("\n\033[3m📍 Main Menu -> Search for a Student\033[0m");
        System.out.println("📝 Please enter the student's username (case sensitive):");
        String username = input.next();
        if (username.isEmpty()) {
            System.out.println("\n⚠️ Oops - no empty inputs please!");
            failedMain("u");
        } else {
            Student student = accountList.searchStudent(username);
            List<Course> studentCourses = accountList.searchStudent(username).getCourses();
            if (student == null) {
                System.out.println("\n⚠️ Course not found!");
                failedMain("u");
            } else {
                System.out.println("Here are " + student.getFullName() + "'s courses:");
                displayCourses(studentCourses);
                System.out.println("Type in \"y\" to see which courses you share, or anything else to return.");
                String command = input.next();
                if (command.equals("y")) {
                    searchSharedWithStudent(student);
                }
            }
        }
    }

    // EFFECTS: returns courses shared with a given student
    private void searchSharedWithStudent(Student student) {
        System.out.println("\n\033[3m📍 Main Menu -> Search for a Student -> View Shared Courses\033[0m");
        System.out.println("Here are the courses you share:");
        List<Course> sharedCourses = student.getSharedCourses(this.currentStudent);
        displayCourses(sharedCourses);
        System.out.println("Press 'enter' to return to the main menu...");
        input.next();
    }

    // EFFECTS: displays the given courses without numbering.
    private void displayCourses(List<Course> sharedCourses) {
        for (Course course : sharedCourses) {
            System.out.println("\t" + course.courseLong());
        }
    }

    // EFFECTS: saves the session to file
    // modelled based on the JsonSerializationDemo file,
    //  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void saveSession() {
        try {
            jsonWriter.open();
            jsonWriter.write(accountList);
            jsonWriter.close();
            System.out.println("✅ Saved the session to " +  JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: signs the user out of the applet.
    private void signOut() {
        currentStudent = null;
        keepGoingMain = false;
        keepGoingLogin = true;
        System.out.println("You've been signed out! See you soon. 👋");
    }

    // EFFECTS: prints out a user's courses, numbered. Used in tandem with other methods that accept user input.
    private void viewCoursesInternal(List<Course> courseList) {
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            System.out.println("\t" + (i + 1) + " -> " + course.courseLong());
        }
    }

    // EFFECTS: method facilitates course entry: used in tandem with other methods
    private List<String> courseEntry() {
        List<String> courseEntry = new ArrayList<>();
        System.out.println("📝 Please enter the course name's shorthand (e.g. CPSC, APSC):");
        String courseName = input.next().toUpperCase();
        System.out.println("📝 Please enter the course number (e.g. 210, 100):");
        String courseNum = input.next();
        System.out.println("📝 Please enter your course section (e.g. 203, 007):");
        String courseSection = input.next();
        courseEntry.add(courseName);
        courseEntry.add(courseNum);
        courseEntry.add(courseSection);

        return courseEntry;
    }

    // MODIFIES: this
    // EFFECTS: processes user command after a fault in any main operations
    private void failedMain(String method) {
        boolean keepGoing = true;
        String command;
        while (keepGoing) {
            System.out.println("Would you like to:");
            System.out.println("\t 1 -> Try again");
            System.out.println("\t 2 -> Go back to the Main Menu");

            command = input.next();

            if (command.equals("1")) {
                keepGoing = false;
                checkMainMethod(method);
            } else if (command.equals("2")) {
                break;
            } else {
                System.out.println("Sorry - I didn't understand that. Try again!");
            }
        }
    }

    // EFFECTS: processes the user inputs to redirect the user correctly when the user makes an incorrect input.
    private void checkMainMethod(String method) {
        if (method.equals("a")) {
            addCourse();
        } else if (method.equals("r")) {
            removeCourse();
        } else if (method.equals("v")) {
            viewCourses();
        } else if (method.equals("c")) {
            searchCourse();
        } else if (method.equals("u")) {
            searchStudent();
        } else {
            System.err.println("This is very bad");
        }
    }
}
