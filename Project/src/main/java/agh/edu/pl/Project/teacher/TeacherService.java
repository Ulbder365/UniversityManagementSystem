package agh.edu.pl.Project.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TeacherService{

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() { return teacherRepository.findAll(); }

    public boolean emailValidation(String email) {
        Pattern pattern = Pattern.compile(".@.");
        Matcher matcher = pattern.matcher(email);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        if(matches == 1) return true;
        else return false;
    }

    public boolean teacherValidation(String name,Integer age,String email)
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

    public void addNewTeacher(Teacher teacher) {
        if(teacherValidation(teacher.getName(), teacher.getAge(), teacher.getEmail()))
            teacherRepository.save(teacher);
        System.out.println(teacher);
    }

    public void deleteTeacherById(Long id) {
        Optional<Teacher> teacherById = teacherRepository.findTeacherById(id);
        if(!teacherById.isPresent())
        {
            throw new IllegalStateException("Cannot delete teacher with given Id. Id does not exist");
        }
        else{
            teacherRepository.deleteById(id);}
    }

    @Transactional
    public void updateTeacher(Long teacherId, String name, String surname, LocalDate dayOfBirth, String email, String subject) {
        Optional <Teacher> studentById = teacherRepository.findTeacherById(teacherId);
        if(!studentById.isPresent())
        {
            throw new IllegalStateException("Cannot update teacher with given Id. Id does not exist");
        }
        else if(teacherValidation(name, Period.between(dayOfBirth, LocalDate.now()).getYears(), email)){
            Teacher teacher =teacherRepository.getById(teacherId);
            teacher.setName(name);
            teacher.setSurname(surname);
            teacher.setDayOfBirth(dayOfBirth);
            teacher.setSubject(subject);
            teacher.setEmail(email);
        }
        else { throw new IllegalStateException("How do I get here");}
    }
}
