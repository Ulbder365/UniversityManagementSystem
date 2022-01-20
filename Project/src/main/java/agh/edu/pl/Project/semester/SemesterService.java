package agh.edu.pl.Project.semester;

import agh.edu.pl.Project.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SemesterService {

    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public void addNewSemester(Semester semester)
    {
        if(semesterValidation(semester.getId())) {
            semesterRepository.save(semester);
            System.out.println(semester);
        }
    }

    public boolean semesterValidation(Long id)
    {
        return true;
    }
}
