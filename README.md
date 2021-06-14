# FMI-MyMento-Project

Android application developed with [@AndreeaGavrila](https://github.com/AndreeaGavrila), [@AlexandraBuruiana](https://github.com/alexandraburu23) and [@MariaFlorea](https://github.com/FloreaMaria)

![Logo](https://github.com/CojocaruAlexandraFlavia/FMI-MyMento-Project/blob/master/images/mymentologo.png)

## Description of the Application and Motivation
Android application for students from Faculty of Mathematics and Informatics from University of Bucharest, in order to connect with each other.

College years are stressful and some of us may not academically perform like expected. Others may want to help other colleagues improve their knowledge, while being able to complete their mandatory practice hours or get some money.

This idea is already implemented at the Faculty of Mathematics and Informatics from University of Bucharest, with group tutoring for first-year-students, but our project is meant to improve it and expand it to all years and all domains.

FMI MyMento is an interactive platform where any student from the faculty can register and be a tutor or student. Students can choose their tutors for every subject they study or studied in the past. They also have recommendations for other courses they can take based on ratings. Tutors can choose from a list of subjects, personalized for their year and domain, add contact information and get in touch with other tutors. After reaching a number of hours, they have the option to download a file for the mandatory practice hours and start receiving money.

We all have benefitted from the group tutoring hours in the past and we appreciate their importance, especially in times like the current global pandemic. Our application is meant to connect the students and find a quicker way to provide and get the help you need.

## Demo
Our app functionalities are presented in this [demo](https://drive.google.com/file/d/1fDCVYM80pdGuc5NRgZ0eGq9Z8IiIMg_M/view?usp=sharing).

## Trello:
Our team has used [Trello](https://trello.com/b/oPfsRaeX) to manage, organize tasks and for backlog planning.

## User Stories
Guided by our own experience, we’ve come up with short descriptions of the features we thought were necessary:
1. As an user, I would like to have the option of searching for tutors and also be a tutor myself.
2. As an user, I would like to get notified when I reach the number of hours required for practice.
3. As an user, I would like to modify my profile data anytime.
4. As an user, I would like to receive money from sharing my knowledge with the other students, depending on the number of hours I spend teaching.
5. As an user, I would like to have the possibility to download a file with all my activity in the app.
6. As an user, I would like to be able to choose the person who's tutoring me.
7. As an user, I would like to get in touch with other experienced colleagues.
8. As an user, I would like to receive feedback from the persons I teach.
9. As an user, I would prefer to teach multiple courses.
10. As an user, I would like to have recommendations of courses I could take.
11. As an user, I would like to manage my students into groups.

## UML Use Case Diagram
![UML](https://github.com/CojocaruAlexandraFlavia/FMI-MyMento-Project/blob/master/images/UML.png)

## Build Tools
Our project was written in Java and developed in Android Studio. The build tool used by us was [Gradle](https://gradle.org/), an open-source build automation tool, designed for multi-project builds and distributed under the Apache License 2.0.

## Bug Reporting

* In order to keep the integrity of our database, we had to constrain some of columns from the "taught_courses" table.
We fixed that by making "idCourseToTeach", "id_FkStudent", "id_FkTutor" unique values.
This led us to another bug, referring to changing the database structure. When we updated the database structure, we needed
to either update its version by simply increasing the current version number or to wipe data from
our emulator and run again.

* The application logo was added to the files, but was not visible on the desired page. Solution: We added the specific attribute
  ```android
  tools: srcCompat =" @ drawable / mymentologo "
  ```
  but it was not enough and the exact source had to be specified
  ```android
  android: src =" @ drawable / mymentologo "
  ```
&nbsp;&nbsp;&nbsp;&nbsp;  To see the solution, click [here](https://github.com/CojocaruAlexandraFlavia/FMI-MyMento-Project/commit/028b77945241e157abee7f269d8019a279d28963).

* We had some issues with the file of the tutor activity, but the most important thing is the fact that we did not have permission to save a file to the Android Emulator.
So that is why we added
  ```android
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
  <uses-permission
        android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="28"
        tools:ignore="ScopedStorage" />
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
   ```
   in AndroidManifest.xml.

 &nbsp;&nbsp;&nbsp;&nbsp;To see the solution, click [here](https://github.com/CojocaruAlexandraFlavia/FMI-MyMento-Project/commit/a2198ebedc42db5115ff0312ab9ac449fdca3e1d#diff-7fa6aef292187a049f7a4d6060d8df3ba212d838789c78940bd363344b1c38cd).

 * Two buttons were used for contacting the tutors (call button and email address button) but, because these buttons are referring to actions outside of our apps, some errors regarding permission denial appeared. We added a specific permission in AndroidManifest.xml
   ```android
   <uses-permission android:name="android.permission.CALL_PHONE" />
   ```

  &nbsp;&nbsp;&nbsp;&nbsp;To see the solution, click [here](https://github.com/CojocaruAlexandraFlavia/FMI-MyMento-Project/commit/6833ee82eb8941231de5999ffc510da2713f7e88).




## Automation Testing
To verify the functionality of the application we used automatic testing using JUnit (current version Junit5). This is a simple but very popular tool. It brought additions to the manual tests performed by us and is also very easy to understand and use, from the first sight. We have created automatic tests for all "Get" and "Set" methods, so that we can be sure that the data that the application extracts through those methods are the very data that we expect to be extracted.

![Test-snippet](https://github.com/CojocaruAlexandraFlavia/FMI-MyMento-Project/blob/master/images/test.png)

## Code Standards
Our project respects [Java Coding Standards](https://google.github.io/styleguide/javaguide.html): components’ names were written for their purpose, classes’ names are nouns starting with uppercase letters, methods’ names are verbs or verb-noun combinations starting with lowercase letters.

## Design Patterns
As a good practice, we used a singleton approach for database handling, developed with [Room Persistence Library](https://developer.android.com/jetpack/androidx/releases/room), which is an abstraction layer over SQLite to guarantee access to the database and to maintain SQLite’s performance.

## Resources

* https://medium.com/@umang.burman.micro/android-room-persistence-library-a-login-example-with-livedata-b6019fe23b0
* https://developer.android.com/training/data-storage/room
* https://developer.android.com/guide/components/intents-common



