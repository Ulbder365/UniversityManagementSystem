package agh.edu.pl.Project.course;

import agh.edu.pl.Project.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addNewCourse(Course course){
        if(courseValidation(course.getId())) {
            courseRepository.save(course);
            System.out.println(course);
        }
    }

    @Transactional
    public void addStudent(Long courseId, Student student){

        Optional <Course> courseById = courseRepository.findCourseById(courseId);

        if(!courseById.isPresent())
        {
            throw new IllegalStateException("Cannot update course with given Id. Id does not exist");
        }
        else if(courseValidation(courseId)) {
            Course course = courseRepository.getById(courseId);
            List<Student> temporary = course.getStudents();

            if(temporary.isEmpty()) {
               temporary = new ArrayList<>();
               temporary.add(student);
            }
            else {
                temporary.add(student);
            }
            course.setStudents(temporary);
        }
    }

    public boolean courseValidation(Long id){
        return true;
    }

    public List<Course> getCourses() { return courseRepository.findAll(); }

    public void updateCourse(Long courseId, String name, List<Student> students, double pointECTS, double hours, String teacher) {

        Optional <Course> courseById = courseRepository.findCourseById(courseId);

        if(!courseById.isPresent())
        {
            throw new IllegalStateException("Cannot update course with given Id. Id does not exist");
        }
        else if(courseValidation(courseId)){
            Course course = courseRepository.getById(courseId);
        }
        else { throw new IllegalStateException("How do I get here");}
    }

    public void deleteCourseById(Long id) {
        {
            Optional <Course> courseById = courseRepository.findCourseById(id);
            if(!courseById.isPresent())
            {
                throw new IllegalStateException("Cannot delete course with given Id. Id does not exist");
            }
            else{
                courseRepository.deleteById(id);}
        }
    }
}
