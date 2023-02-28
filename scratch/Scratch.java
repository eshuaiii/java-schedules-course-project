// this file contains scratch work. Please don't grade this! Thanks!

/*
    --- Thinking about classes ---
    We probably need an User class -> fields username, password, ListOfCourses kinda thing?
    In that case we also need Course and ListOfCourses as the arbitrary type.
    Course should store deets (course name, course num, date/time rn or later?, and...its students? MR OMG
    ListOfCourses would be the arbitrary type for each Account with an array of courses...wait.
        That can just be a field in Account.
    As for new packages...yeah I hope not. Maybe one for Account and one for Course? Naw not really necessary atm...
        Imports? No actual clue whatsoever. EMOJIS!

    But when I create a new Course, I'm going to need a main group of all course references.
        Would this be an instance of ListOfCourses? Created in main, probably.
    But then...I need to make sure that stays even if a user logs out.
        Initialization step and login/out flow must be diff

    --- Thinking about user stories ---
    "As a user, I want to be able to log in and sign up in the applet"
        - CourseApp.signup: prompt user for name, username, password.
          Account.signup: does internal workflow to add user to DB.
            - New course workflow (keep the "give and return system")? This would be another method in...CourseApp?
                - Different method not part of Account......or honestly it can too (one new, one for normal)
            - CourseApp signup (prompting user deets), then Account public -> private signup to perform ugly stuff
            - checks: username already exists, username not alphanumeric, first + last name must exist, password >8 ?
                      fields empty (courseapp + account)
        - CourseApp.login: username, password.
          Account.login: does internal workflow to check for user
            - What's a good way to store passwords? Go naive first, then keep going
            - Once again, CourseApp login to ask for deets, then private to check
            - checks: password incorrect, username dne, either one field empty (courseapp + account)
    "As a user, I want to be able to add a course that I am taking."
    "As a user, I want to be able to store an arbitrary number of courses that I'm taking"
        - CourseApp.addCourse: prompts for name of course, number, section, date/time?, prof?
            - Implementing an autofill feature: that's for later on; can take advantage of UBCGrades
          Account.addCourse: adds the course into the account's ListOfCourses field,
          Course.addStudent: and adds the student into that course's ListOfStudents field.
        - checks: adding a course that doesn't exist at all, that does exist, that already exists in user, missing deets
                  fields have incorrect inputs?
    "As a user, I want to be able to remove a course that I am no longer taking"
        - CourseApp.removeCourse: numbered list of all the courses one is taking, prompts for number
          Account.removeCourse: removes the course from that account's ListOfCourses field,
          Course.removeStudent: and removes the student from that course's ListOfStudents field.
        - checks: selecting a number that is not in the list, invalid input
    "As a user, I want to be able to view the courses that I am registered in."
        - CourseApp.viewCourses: see all the courses that one is registered in
            - Be able to call methods to add/remove classes
            - Type in a number to see everyone in that class
            - Also be able to call method to view classmates
          Account.viewCourses: parse through all the courses in ListOfCourses
        - checks: no courses, one course, many courses
    "As a user, I want to be able to view the names of other users that share a course as I do."
        - Course.viewClassmates: given a course, see who shares that course.
        - checks: a course that doesn't exist, a course with one student (just them), a course with many
    "As a user, I want to be able to search for a course and see who is in that course."
        - CourseApp.searchCourse: type in a name of a course and get results
          Course.search: once details are provided, search for given element
            - How best should I do this? Give key + value? Do dictionaries exist in Java?
          Course.viewClassmates: once the course is found, return classmates
        - checks: course does not exist, missing info, same checks as above
    "As a user, I want to be able to search for a user and see which courses I share with them."
        - CourseApp.searchStudentCourses: type in username/name of student
          Account.seeSharedCourses: parses through both lists to compare what courses in common.
            - Could also call Course.viewClassmates to see other students that share the course?
        - checks: student name does not exist, no courses shared, one course shared, many shared.


    --- Questions ---
    - Is there such thing as multi-reference fields like in Wix?
        Otherwise right now when a user adds a new course, we'd create the course if it doesn't exist, add it to
        the listofcourses field, and also add the user into the course object. Yikes.
    - Is there a good way to store passwords?
        - Do I need to import a library?
    - What kind of data type would work best for my data?
    - How should I represent the main ListOfCourses?
    - If a function returns a new object, is that MODIFIES?
    - Check model.course - do I need to modify student again in course?

    --- FUTURE IDEAS ---
    - Refactor "press enter to return" into one method
    - Refactor viewCoursesInternal to viewCoursesInternalWithInput, then handle and return the user's input
    - Refactor the check for empty fields as a method OR find a way to do so with exceptions?
    - Change breadcrumbs?
    - Add colours?
    - Make it possible for someone to search for everyone in a course, independent of section
    - Search by username AND name?
     */


public class Scratch {
    /* --- ACTUALLY NO THIS ISN'T RIGHT: CODE TO PUT INTO UI.viewClassmates ---
        ArrayList<String> classmatesCleaned = new ArrayList<String>();

        for (Student s : classmates) {
            do something to classmatesCleaned
        }
        ArrayList<String> classmatesSorted = classmatesCleaned.sort();
        return classmatesSorted;

    --- CODE FOR UI.getSharedCourses---
    Start with AllAccounts.searchStudent, then Student.getSharedCourses()

    Add/remove courses: first search for the course, THEN call the respective add/remove.

    */
    // PREVIOUSLY IN AllAccounts: now use searchStudent, then getSharedCourses
    // EFFECTS: given a username, searches for a student in accountList.
    //          If found, returns list of courses, null otherwise.
    public Collection<Course> searchStudentCourses(String username) {
        Account requiredAccount = accountList.get(username);
        Student requiredStudent;

        if (requiredAccount == null) {
            return null; // TODO: throw StudentNotFoundException
        } else {
            requiredStudent = requiredAccount.getStudent();
        }

        return requiredStudent.getCourseList().values();

    }

    // PREVIOUSLY IN AllAccounts: can use course
    // EFFECTS: parsing through each Account, checks if Account has course, and adds the student if so.
    //          If no classmates exist, returns empty list.
    public List<Student> viewClassmates(Course course) {
        ArrayList<Student> classmates = new ArrayList<Student>();

        for (Account a : accountList.values()) {
            // inside the account, get the student
            Student currentStudent = a.getStudent();
            // then its hashmap courseList, then the course by key if it exists.
            Course hasCourse = currentStudent.getCourseList().get(course.courseToKey());

            if (hasCourse != null) {
                classmates.add(currentStudent);
            }
        }

        return classmates;

    }

    // PREVIOUSLY IN Course, IDK why it's here
    // MODIFIES: this
    // EFFECTS: searches through studentList for the given student. Returns true if found, false otherwise.
    public Boolean searchStudent(Student student) {
        return false; // stub
    }
}
