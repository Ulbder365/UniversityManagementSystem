package agh.edu.pl.Project.semester;

import agh.edu.pl.Project.course.Course;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Semester {

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
    @OneToMany
    @JoinColumn(name= "course_id")
    private List<Course> courses;

    public Semester(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public Semester(){}
}
