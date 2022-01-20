package agh.edu.pl.Project.student;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Getter
@Setter
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    private String name;
    private String surname;
    private LocalDate dayOfBirth;
    @Transient
    private Integer age;
    private String email;
    private String major;

    public Student() {
    }

    public Student(String name, String surname, LocalDate dayOfBirth, Integer age, String email, String major) {
        this.name = name;
        this.surname = surname;
        this.dayOfBirth = dayOfBirth;
        this.age = age;
        this.email = email;
        this.major = major;
    }

    public Integer getAge() {
        return Period.between(this.dayOfBirth, LocalDate.now()).getYears();
    }
}
