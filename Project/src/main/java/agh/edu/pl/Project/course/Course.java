package agh.edu.pl.Project.course;

import agh.edu.pl.Project.student.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Course {

    @Id
    @SequenceGenerator(
            name = "grade_sequence",
            sequenceName = "grade_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grade_sequence"
    )
    private Long id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name= "student_id")
    private List<Student> students;
    private double pointECTS;
    private double hours;
    private String teacher;

    public Course() {
    }

    public Course(Long id, String name, List<Student> students, double pointECTS, double hours, String teacher) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.pointECTS = pointECTS;
        this.hours = hours;
        this.teacher = teacher;
    }
}
