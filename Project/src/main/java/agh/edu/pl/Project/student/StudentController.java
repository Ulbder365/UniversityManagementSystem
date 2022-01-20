package agh.edu.pl.Project.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents(@RequestParam int page, @RequestParam Sort.Direction direction) {
        return studentService.getStudents(page, direction);
    }

    @GetMapping("/students/{id}")
    public List<Student> getStudentById(@RequestParam int page, @RequestParam Sort.Direction direction) {
        return studentService.getStudents(page, direction);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudentById(@PathVariable("studentId") Long id){
        studentService.deleteStudentById(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String dayOfBirth,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String major) {
        studentService.updateStudent(studentId, name, surname, dayOfBirth, email, major);
    }

}
