package ui;

import model.account.AllAccounts;
import model.course.AllCourses;
import model.account.Student;
import model.course.Course;

import java.util.*;

public class CourseApp {
    AllAccounts accountList;
    AllCourses courseList;
    Scanner input;
    Student currentStudent;
    Boolean keepGoingLogin;
    Boolean keepGoingMain;

    // EFFECTS: creates a new CourseApp and initializes fields + Scanner, then begins at preLoggedInScreen.
    public CourseApp() {
        keepGoingLogin = true;
        currentStudent = null;
        accountList = new AllAccounts();
        courseList = new AllCourses();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        preLoggedInScreen();
    }

    // EFFECTS: shows the state before the user is logged in
    private void preLoggedInScreen() {
        String command = null;

        while (keepGoingLogin) {
            System.out.println("\n\033[3m📍 Login Page\033[0m");
            System.out.println("Hi! 👋 Select from the following:");
            System.out.println("\tl -> Login");
            System.out.println("\ts -> Signup");
            System.out.println("\tq -> Quit the applet");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("l")) {
                login();
            } else if (command.equals("s")) {
                signup();
            } else if (command.equals("q")) {
                keepGoingLogin = false;
                System.out.println("\nGoodbye! See you soon.");
            } else {
                System.out.println("\nSorry - I didn't understand that. Try again!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: takes in user inputs for signup workflow.
    //          If no incorrect inputs, creates a new student and adds to accountList.
    private void signup() {
        // TODO: figure out how to capitalize names! How to import apache?
        // TODO: possible refactor since username check already exists in AllAccounts?
        System.out.println("\n\033[3m📍 Login Page -> Signup\033[0m");
        System.out.println("📝 Please enter your username:");
        String username = input.next();
        System.out.println("📝 Please enter your first name:");
        String firstName = input.next();
        System.out.println("📝 Please enter your last name:");
        String lastName = input.next();
        System.out.println("📝 Please enter your password:");
        String password = input.next();
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
            System.out.println("\n⚠️ Oops - no empty inputs please!");
            failedSignUpLogIn("s");
        } else if (accountList.getAccountList().get(username) != null) {
            System.out.println("\n⚠️ Oops - seems like that username is taken.");
            failedSignUpLogIn("s");
        } else {
            currentStudent = accountList.signup(username, firstName, lastName, password);
            System.out.println("\n✅ Signup successful!");
            keepGoingLogin = false;
            keepGoingMain = true;
            mainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: Takes user inputs and attempts to log user in. If successful, user's Student is assigned and
    //          user is redirected to main menu.
    private void login() {
        System.out.println("\n\033[3m📍 Login Page -> Log In\033[0m");
        System.out.println("🔒 Please enter your username:");
        String username = input.next();
        System.out.println("🔒 Please enter your password:");
        String password = input.next();

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
                keepGoingLogin = false;
                keepGoingMain = true;
                mainMenu();
            }
        }
    }

    // EFFECTS: processes user command after sign up or log in fails
    private void failedSignUpLogIn(String method) {
        Boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Would you like to:");
            System.out.println("\t 1 -> Try again");
            System.out.println("\t 2 -> Go back to Main Login page");

            String command = input.next();
            if (command.equals("1")) {
                keepGoing = false;
                if (method.equals("s")) {
                    signup();
                } else if (method.equals("l")) {
                    login();
                } else {
                    System.err.println("This is very bad");
                }
            } else if (command.equals("2")) {
                break;
            } else {
                System.out.println("Sorry - I didn't understand that. Try again!");
            }
        }
    }

    // EFFECTS: produces the main menu after a user is logged in.
    private void mainMenu() {
        String command = null;

        while (keepGoingMain) {
            System.out.println("\n\033[3m📍 Main Menu \033[0m");
            System.out.println("Hi, " + currentStudent.getFirstName() + "! 😃");
            mainMenuOptions();

            command = input.next();
            command = command.toLowerCase();
            mainMenuProcessCommands(command);
        }
    }

    // EFFECTS: produces the main menu options
    private void mainMenuOptions() {
        System.out.println("\nSelect from the options below:");
        System.out.println("\t v -> View all your registered courses and your classmates");
        System.out.println("\t c -> Search for a course and view enrolled students");
        System.out.println("\t u -> Search for a user and view shared courses");
        System.out.println("\t a -> Add a course to your account");
        System.out.println("\t r -> Remove a course from your account");
        System.out.println("\t s -> Sign out");
        System.out.println("\t q -> Quit the applet");
    }

    // MODIFIES: this
    // EFFECTS: processes user command.
    private void mainMenuProcessCommands(String command) {
        if (command.equals("v")) {
            viewCourses();
        } else if (command.equals("c")) {
            searchCourse();
        } else if (command.equals("u")) {
            searchStudent();
        } else if (command.equals("a")) {
            addCourse();
        } else if (command.equals("r")) {
            removeCourse();
        } else if (command.equals("s")) {
            signOut();
        } else if (command.equals("q")) {
            System.out.println("Have a great day! See you soon.");
            keepGoingMain = false;
            keepGoingLogin = false;
        } else {
            System.out.println("Sorry - I didn't understand that. Try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a course to the currentStudent.
    // TODO: remove redundancy with this and searchCourse (have search method that returns a list of values?)
    @SuppressWarnings("methodlength")
    private void addCourse() {
        System.out.println("\n\033[3m📍 Main Menu -> Add Course\033[0m");
        System.out.println("📝 Please enter the course name's shorthand (e.g. CPSC, APSC):");
        String courseName = input.next().toUpperCase();

        Boolean isSuccessful = false;
        Integer courseNum = -1;
        Integer courseSection = -1;
        try {
            System.out.println("📝 Please enter the course number (e.g. 210, 100):");
            courseNum = input.nextInt();
            System.out.println("📝 Please enter your course section (e.g. 203, 007):");
            courseSection = input.nextInt();
            isSuccessful = true;
        } catch (InputMismatchException e) {
            System.out.println("Oops - the course number and section must be an integer!");
            failedMain("a");
        }

        if (courseName.isEmpty() || courseNum.equals(-1) || courseSection.equals(-1)) {
            System.out.println("\n⚠️ Oops - no empty inputs please!");
            failedMain("a");
        } else {
            Course course = courseList.addCourse(courseName, courseNum, courseSection);
            currentStudent.addCourse(course);
            Course checkCourse = currentStudent.getCourseList().get(course.courseToKey());
            System.out.println("\n✅ Course added: " + checkCourse.courseLong());
        }
    }

    // EFFECTS: processes user command after a fault in any main operations
    // TODO: move this method around so to avoid this warning
    @SuppressWarnings("methodlength")
    private void failedMain(String method) {
        Boolean keepGoing = true;
        String command = null;
        while (keepGoing) {
            System.out.println("Would you like to:");
            System.out.println("\t 1 -> Try again");
            System.out.println("\t 2 -> Go back to the Main Menu");

            command = input.next();

            if (command.equals("1")) {
                keepGoing = false;
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
            } else if (command.equals("2")) {
                break;
            } else {
                System.out.println("Sorry - I didn't understand that. Try again!");
            }
        }
    }

    // MODIFIES: this
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
                Integer command = input.nextInt();
                try {
                    Course removedCourse = courseList.get(command - 1);
                    currentStudent.removeCourse(removedCourse);
                    this.courseList.checkForEmptyCourse(removedCourse);
                    System.out.println("\n✅ Course removed!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops - that's not a valid selection!");
                    failedMain("r");
                }
                // TODO: weird issue about double printing?
            } catch (InputMismatchException e) {
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
        } else {
            System.out.println("\n📝 Here are your courses! Select a course to view your classmates,"
                                 + "or any other number to go back to the main menu.");
            viewCoursesInternal(courseList);
            try {
                Integer command = input.nextInt();
                try {
                    Course viewCourse = courseList.get(command - 1);
                    viewClassmates(viewCourse);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Returning to the Main Menu.");
                }
                // TODO: weird issue about double printing?
            } catch (InputMismatchException e) {
                System.out.println("Oops - your selection must be an integer!");
                failedMain("v");
            }
        }
    }

    // EFFECTS: prints out a user's courses, numbered. Used in tandem with other methods that accept user input.
    private void viewCoursesInternal(List<Course> courseList) {
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            System.out.println("\t" + (i + 1) + " -> " + course.courseLong());
        }
    }

    // EFFECTS: shows all the students enrolled in a given course
    private void viewClassmates(Course course) {
        System.out.println("\n\033[3m📍 Main Menu -> View Courses -> View Students in Course\033[0m");
        System.out.println("Students in " + course.courseLong());
        System.out.println(course.getStudentListSorted());
    }

    // EFFECTS: searches for a course given user inputs
    @SuppressWarnings("methodlength")
    private void searchCourse() {
        System.out.println("\n\033[3m📍 Main Menu -> Search for a Course\033[0m");
        System.out.println("📝 Please enter the course name's shorthand (e.g. CPSC, APSC):");
        String courseName = input.next().toUpperCase();

        Boolean isSuccessful = false;
        Integer courseNum = -1;
        Integer courseSection = -1;
        try {
            System.out.println("📝 Please enter the course number (e.g. 210, 100):");
            courseNum = input.nextInt();
            System.out.println("📝 Please enter your course section (e.g. 203, 007):");
            courseSection = input.nextInt();
            isSuccessful = true;
        } catch (InputMismatchException e) {
            System.out.println("Oops - the course number and section must be an integer!");
            failedMain("c");
        }

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
    @SuppressWarnings("methodlength")
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
                for (int i = 0; i < studentCourses.size(); i++) {
                    Course course = studentCourses.get(i);
                    System.out.println("\t" + course.courseLong());
                }
                System.out.println("Type in \"y\" to see which courses you share, or anything else to return.");
                String command = input.next();
                if (command.equals("y")) {
                    searchSharedWithStudent(student);
                }
            }
        }
    }

    // runs commands to search for courses shared with a student
    private void searchSharedWithStudent(Student student) {
        System.out.println("\n\033[3m📍 Main Menu -> Search for a Student -> View Shared Courses\033[0m");
        System.out.println("Here are the courses you share:");
        List<Course> sharedCourses = student.getSharedCourses(this.currentStudent);
        for (int i = 0; i < sharedCourses.size(); i++) {
            Course course = sharedCourses.get(i);
            System.out.println("\t" + course.courseLong());
        }
    }

    //
    private void signOut() {
        currentStudent = null;
        keepGoingMain = false;
        keepGoingLogin = true;
        System.out.println("You've been signed out! See you soon. 👋");
    }
}
