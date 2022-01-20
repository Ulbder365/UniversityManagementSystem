package agh.edu.pl.Project.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public void addNewGrade(Grade grade){
        if(gradeValidation(grade.getId())) {
            gradeRepository.save(grade);
            System.out.println(grade);
        }
    }

    public boolean gradeValidation(Long id){
        return true;
    }
}
