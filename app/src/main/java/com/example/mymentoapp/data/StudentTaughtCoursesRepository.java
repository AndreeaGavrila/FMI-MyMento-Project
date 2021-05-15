package com.example.mymentoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.StudentWithTaughtCourses;
import com.example.mymentoapp.model.TaughtCourseStudentCross;
import com.example.mymentoapp.model.TutorWithCourse;
import com.example.mymentoapp.util.MyRoomDatabase;

import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class StudentTaughtCoursesRepository {

    private StudentTaughtCoursesDao studentTaughtCoursesDao;
    private StudentDao studentDao;
    private LiveData<List<StudentWithTaughtCourses>> allStudentsWithTaughtCourses;
    private CourseToTeachDao courseToTeachDao;
    protected List<CourseToTeach> allCoursesToTeach;
    private LiveData<List<CourseToTeach>> courses;

    public StudentTaughtCoursesRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        studentTaughtCoursesDao = db.studentTaughtCoursesDao();
        courseToTeachDao = db.courseToTeachDao();
        studentDao = db.studentDao();
        allStudentsWithTaughtCourses = studentTaughtCoursesDao.getAll();
        allCoursesToTeach = courseToTeachDao.getAllToTeachCourses().getValue();
        courses = courseToTeachDao.getAllToTeachCourses();
        System.out.println("courses" + courses.getValue());
        System.out.println(allCoursesToTeach);
    }

    public void insertTaughtCourses(StudentWithTaughtCourses studentWithTaughtCourses){
        System.out.println("before new student insert async");
       new StudentTaughtCoursesRepository.insertAsync(studentTaughtCoursesDao, allCoursesToTeach).execute(studentWithTaughtCourses);
        System.out.println("dupa async");
    }

    public static class insertAsync extends AsyncTask<StudentWithTaughtCourses, Void, Void> {


        private StudentTaughtCoursesDao studentTaughtCoursesDaoAsync;
        private List<CourseToTeach> allCourseToTeach;
        insertAsync(StudentTaughtCoursesDao studentTaughtCoursesDao, List<CourseToTeach> allCoursesToTeach){
            studentTaughtCoursesDaoAsync = studentTaughtCoursesDao;
            allCourseToTeach = allCoursesToTeach;
        }

        @Override
        protected Void doInBackground(StudentWithTaughtCourses... studentWithTaughtCourses) {
            long identifier = studentTaughtCoursesDaoAsync.insertStudent(studentWithTaughtCourses[0].getStudent());
            System.out.println("first in background");
            TaughtCourseStudentCross studentCross = new TaughtCourseStudentCross();
            studentCross.setIdStudent((int)identifier);
            System.out.println("do in background" + studentCross.getIdStudent());

            for (CourseToTeach c: allCourseToTeach) {
                System.out.println("course_to_teach" + c.getIdCourseToTeach() + c.getCourseName());
            }

            return null;
        }
    }
}
