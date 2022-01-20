package agh.edu.pl.Project.course;

import agh.edu.pl.Project.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public void registerNewCourse(@RequestBody Course course) {courseService.addNewCourse(course);}

    @GetMapping
    public List<Course> getCourses() { return courseService.getCourses(); }

 //   @PutMapping(path = "{courseId}")
 //  public void updateCourse(
 //           @PathVariable("courseId") Long courseId,
 //           @RequestParam(required = false) String name,
 //           @RequestParam(required = false) List<Student> students,
 //           @RequestParam(required = false) double pointECTS,
 //           @RequestParam(required = false) double hours,
 //           @RequestParam(required = false) String teacher){
 //       courseService.updateCourse(courseId, name, students, pointECTS, hours, teacher);
 //   }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourseById(@PathVariable("courseId") Long id){courseService.deleteCourseById(id);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourseStudentList(
            @PathVariable("courseId") Long courseId,
            @RequestBody Student student){
        courseService.addStudent(courseId,student);
    }
}
