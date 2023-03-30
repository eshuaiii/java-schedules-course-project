package ui;

import model.account.AllAccounts;
import model.course.AllCourses;
import model.account.Student;
import model.course.Course;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Creates a new course app instance.
public class CourseApp {
    private static final String JSON_STORE = "./data/session.json";
    private AllAccounts accountList;
    private AllCourses courseList;
    private Scanner input;
    private Student currentStudent;
    private Boolean keepGoingLogin;
    private Boolean keepGoingMain;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates a new CourseApp and initializes fields + Scanner, then begins at preLoggedInScreen.
    public CourseApp() throws FileNotFoundException {
        keepGoingLogin = true;
        currentStudent = null;
        accountList = new AllAccounts();
        courseList = new AllCourses();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        preLoggedInScreen();
    }

    // MODIFIES: this
    // EFFECTS: shows the state before the user is logged in
    // TODO: fix length by moving options or menu to own method
    @SuppressWarnings("methodlength")
    private void preLoggedInScreen() {
        String command;

        while (keepGoingLogin) {
            System.out.println("\n\033[3m📍 Login Page\033[0m");
            System.out.println("Hi! 👋 Select from the following:");
            System.out.println("\tl -> Login");
            System.out.println("\ts -> Signup");
            System.out.println("\tload -> Load a previous session");
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
            } else if (command.equals("load")) {
                loadSession();
            } else {
                System.out.println("\nSorry - I didn't understand that. Try again!");
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
    private void signup() {
        // TODO: figure out how to capitalize names! How to import apache?
        // TODO: possible refactor since username check already exists in AllAccounts? -> would go over 25
        // TODO: allow spaces in username?
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

    // MODIFIES: this
    // EFFECTS: processes user command if sign up or log in fails
    private void failedSignUpLogIn(String method) {
        boolean keepGoing = true;
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
        String command;

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
        System.out.println("\t save -> Save this session to file.");
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
        } else if (command.equals("save")) {
            saveSession();
        } else if (command.equals("q")) {
            System.out.println("Have a great day! See you soon. 👋");
            keepGoingMain = false;
            keepGoingLogin = false;
        } else {
            System.out.println("Sorry - I didn't understand that. Try again!");
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
