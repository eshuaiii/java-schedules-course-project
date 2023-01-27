# <center>📅 ️A small schedule-sharing tool</center>

*(a temporary name until I can find a better one :D)*

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