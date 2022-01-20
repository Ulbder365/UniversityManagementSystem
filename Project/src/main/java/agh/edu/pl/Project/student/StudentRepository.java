package agh.edu.pl.Project.student;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional <Student> findStudentById(Long Id);

    @Query("Select s From Student s")
    List<Student> findAllStudents(Pageable page);

}
