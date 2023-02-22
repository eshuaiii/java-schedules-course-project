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

     Question: is there such thing as multi-reference fields like in Wix?
        Otherwise right now when a user adds a new course, we'd create the course if it doesn't exist, add it to
        the listofcourses field, and also add the user into the course object. Yikes.

    --- Thinking about user stories ---
    "As a user, I want to be able to log in and sign up in the applet"
        - Account.signup: prompt user for name, username, password.
            - New course workflow (keep the "give and return system")? This would be another method in...CourseApp?
                - Different method not part of Account......or honestly it can too (one new, one for normal)
            - CourseApp signup (prompting user deets), then Account public -> private signup to perform ugly stuff
            - checks: username already exists, username not alphanumeric, first + last name must exist, password >8 ?
                      fields empty (courseapp + account)
        - Account.login: username, password.
            - What's a good way to store passwords? Go naive first, then keep going
            - Once again, CourseApp login to ask for deets, then private to check
            - checks: password incorrect, username dne, either one field empty (courseapp + account)
    "As a user, I want to be able to add a course that I am taking."
        - Account.addCourse:


     */

public class Scratch {

}
