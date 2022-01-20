package agh.edu.pl.Project.teacher;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Getter
@Setter
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long id;

    private String name;
    private String surname;
    private LocalDate dayOfBirth;
    @Transient
    private Integer age;
    private String email;
    private String subject;

    public Teacher() {
    }

    public Teacher(String name, String surname, LocalDate dayOfBirth, Integer age, String email, String subject) {
        this.name = name;
        this.surname = surname;
        this.dayOfBirth = dayOfBirth;
        this.age = age;
        this.email = email;
        this.subject = subject;
    }

    public Integer getAge() {
        return Period.between(this.dayOfBirth, LocalDate.now()).getYears();
    }
}


