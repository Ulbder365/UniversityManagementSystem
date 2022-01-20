package agh.edu.pl.Project.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private static final int size = 5;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(int page, Sort.Direction direction) {
        return studentRepository.findAllStudents(PageRequest.of(page,size, Sort.by(direction, "id")));
    }

    public boolean emailValidation(String email) {
        Pattern pattern = Pattern.compile(".@.");
        Matcher matcher = pattern.matcher(email);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches == 1;
    }

    public boolean studentValidation(String name,Integer age,String email)
    {
        if(name.length()<3)
        {
            throw new IllegalStateException("Name cannot be empty or shorter than 3 letters");
        }
        if(age<18)
        {
            throw new IllegalStateException("Student must be older than 18");
        }
        if(!emailValidation(email))
        {
            throw new IllegalStateException("Valid email");
        }
        else return true;
    }

    public void addNewStudent(Student student) {
        if(studentValidation(student.getName(), student.getAge(), student.getEmail()))
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudentById(Long id)
    {
        Optional <Student> studentById = studentRepository.findStudentById(id);
        if(!studentById.isPresent())
        {
            throw new IllegalStateException("Cannot delete student with given Id. Id does not exist");
        }
        else{
        studentRepository.deleteById(id);}
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String surname, String dayOfBirth, String email, String major)
    {
        LocalDate dayOfBirthDate = LocalDate.parse(dayOfBirth);
        Optional <Student> studentById = studentRepository.findStudentById(studentId);
        if(!studentById.isPresent())
        {
            throw new IllegalStateException("Cannot update student with given Id. Id does not exist");
        }
        else if(studentValidation(name,Period.between(dayOfBirthDate, LocalDate.now()).getYears(), email)){
        Student student =studentRepository.getById(studentId);
            student.setName(name);
            student.setSurname(surname);
            student.setDayOfBirth(dayOfBirthDate);
            student.setMajor(major);
            student.setEmail(email);
        }
        else { throw new IllegalStateException("How do I get here");}
    }
}
