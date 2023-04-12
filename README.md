<h1 align="center"> 📅 ️A small schedule-sharing tool</h1>

---

## 🌱 Introduction
In high school, I created a website using Wix that allowed my classmates to add their timetables 
and see who was in their classes. However, there were some limitations, such as me having to
manually add in the student after they signed up (moving their details from a Google Form to the database),
and a slow interface overall.

I want to extend this idea further into my CPSC 210 term project and recreate this idea. The applet would
store different user accounts (locally to satisfy the internet constraint), then enable each user to input
in their courses. The applet would then store these to their account, and parse through to find other students
who are in their classes.

---
## 💾 What will the application do?

**The application will (main lines of inquiry):**
- Store basic user data (username and password).
- Allow the user to sign in, sign up, and for  all data to be saved.
- Allow the user to add an arbitrary amount of courses by inputting
their course subject, number, and section.
  - Stores this data with the user.
  - Allow the user to add or remove their courses.
- Allow the user to select a course from a list of their courses to see who is in their section.
- Allow the user to search for a course to see who is in that class.
- Allow the user to search for another user and see which courses they share with the user.

**Additional inquiries of interest** (potentially very difficult, but just ideas I have):
- Adding in calendar functionality
  - Allow the user to add in the times of their course OR
  - Allow the user to upload an .ical file downloaded from SSC.
  - Let the user view their schedule, or other student's schedules, in "day" form
  - Allow the user to compare schedules with someone else to see blocks of free time they share with another user.
- An autocomplete function when adding or searching for a course
- A way to connect with other users on the platform
  - Being able to send requests to users and receive from users; which the applet
  would then share their social media with each other.

## 🧑‍🦰 Who will use it?

- Students in middle or high school, who wish to know who is in their classes
- Students in university who wish to know which classes they share with each other 
- Students in university looking to get to know other students in their classes

## ✨ Why is this a project of interest to you?

As I illustrated in the introduction, I made a similar project during high school to enable my peers to see who share
their classes, but I was not the greatest in web development and had a lot of limitations in the project.
I want to use this opportunity to take my initial project and make it better! 

As well, now that I'm in university, I find myself often asking other friends for their schedules, only to have to manually
compare low-resolution screenshots to figure out which courses I have with them. This was another source of inspiration
for this project - being able to now simplify this process and immediately see which classes I share, as well as being
able to access this information again (instead of having to compare again) would enable me (and others) to find peers
to reach out and study with faster.

---

## 👤 User Stories

- As a user, I want to be able to log in and sign up in the applet.
- As a user, I want to be able to add a course that I am taking.
- As a user, I want to be able to store an arbitrary number of courses that I'm taking.
- As a user, I want to be able to remove a course that I am no longer taking.
- As a user, I want to be able to view the courses that I am registered in.
- As a user, I want to be able to view the names of other users that share a course as I do.
- As a user, I want to be able to search for a course and see who is in that course.
- As a user, I want to be able to search for a user and see which courses I share with them.
- As a user, I want to be able to save the current user accounts and students to file when I choose to.
- As a user, I want to be able to reload the program state from a file when I choose to.

## 🛝 Instructions for Grader
Sorry for the ugliness! I will try to make it look nicer in Phase 4.
For reference, these three user accounts can be accessed with some pre-filled information. Feel free to modify at your liking!

- Username: eric, Password: eric
- Username: bob, Password: bob
- Username: jerry, Password: jerry



- You can generate the first required action related to adding Xs to a Y by removing a course from any user
- You can generate the second required action related to adding Xs to a Y by adding a course to a user
- You can locate my visual component at the splash screen
- You can save the state of my application by logging into any account and choosing "Save"
- You can reload the state of my application before logging in by pressing "Load"

## 📜 Phase 4, Task 2
>️ ℹ️ Note: Loading data creates a bit of a messy log, as every event is logged.

| **Action Performed**                                                       | **Log**                                                                                                                                                                                                                                                                        |
|----------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Loading data from file                                                     | ...<br/>**Tue Apr 11 16:22:56 PDT 2023 <br/>📜 Successfully loaded the data from./data/session.json**                                                                                                                                                                          |
| Account doesn't exist                                                      | Tue Apr 11 17:22:46 PDT 2023<br/>⚠️ Account erc was not found.                                                                                                                                                                                                                 |
| Attempting to login with an incorrect password                             | Tue Apr 11 16:23:02 PDT 2023 <br/>⚠️ Incorrect password for: eric                                                                                                                                                                                                              |
| Successful Login                                                           | Tue Apr 11 16:23:05 PDT 2023<br/>✅ eric has logged in.                                                                                                                                                                                                                         |
| Signing up                                                                 | Tue Apr 11 17:41:24 PDT 2023<br/>✅ Signup for apple was successful.                                                                                                                                                                                                            |
| Removing a course from an account (with no more students in the course)    | Tue Apr 11 16:23:09 PDT 2023<br/>✅ Student eric removed from PSYC 101, Section 107<br/>Tue Apr 11 16:23:09 PDT 2023<br/>✅ Course: PSYC 101, Section 107 removed from eric<br/>Tue Apr 11 16:23:09 PDT 2023<br/>ℹ️ Empty course: PSYC 101, Section 107 removed from courseList. |
| Adding a course to an account (that doesn't exist in the all courses list) | Tue Apr 11 16:23:16 PDT 2023<br/>✅ Course: PSYC 101, Section 107 added to courseList.<br/>Tue Apr 11 16:23:16 PDT 2023<br/>✅ Student eric added to PSYC 101, Section 107<br/>Tue Apr 11 16:23:16 PDT 2023<br/>✅ Course: PSOC 101, Section 107 added to eric                    |
| Removing a course from an account (with more students)                     | Tue Apr 11 17:30:07 PDT 2023<br/>✅ Student eric removed from CPSC 121, Section 202<br/>Tue Apr 11 17:30:07 PDT 2023<br/>✅ Course: CPSC 121, Section 202 removed from eric                                                                                                      |
| Adding a course to an account (that exists in the all courses list)        | Tue Apr 11 17:30:12 PDT 2023<br/>ℹ️ Course: CPSC 121, Section 202 exists in courseList.<br/>Tue Apr 11 17:30:12 PDT 2023<br/>✅ Student eric added to CPSC 121, Section 202<br/>Tue Apr 11 17:30:12 PDT 2023<br/>✅ Course: CPSC 121, Section 202 added to eric                  |
| Saving data to file                                                        | Tue Apr 11 16:23:55 PDT 2023<br/>📜 Saved the session to ./data/session.json                                                                                                                                                                                                   |

## 🪴 Phase 4, Task 3

Although I'm happy with how this project is as it stands today, there are a lot of refactoring that I hope I can do in the future:

- **Adding Exceptions:** Instead of using `if/else` blocks and returning different values depending on the situation, I want to refactor the code to implement Exceptions for anything that shouldn't happen. This way, we clearly separate the control and error flows and can handle each error case better.
- **Refactoring `CourseApp` and `CourseAppGUI`:** These two classes are almost identical to each other, to the point that I had to group them together on the UML diagram. Having an abstract class that implements most of the methods shared by both, and overriding unique points of variance between the console and GUI-based programs would be great.
- **Using the Singleton Method for `CourseAppGUI`:** Although I'm happy that I separated each frame into its own class, it brings up the challenge that now, I have to pass in the CourseAppGUI object into each of the frames that I use. There's a lot of redundancy, and I might be able to fix this by creating CourseAppGUI as a Singleton class; that way, all classes can simply grab and update the required fields (as there is only one GUI instance).

Thank you so much!