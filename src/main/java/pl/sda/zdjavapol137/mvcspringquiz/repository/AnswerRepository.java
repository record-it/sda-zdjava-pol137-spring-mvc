package pl.sda.zdjavapol137.mvcspringquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // napisz metodę zwracajacą listę encji Answer z podaną wartością userAnswer
    List<Answer> findDByUserAnswer(String userAnswer);


}
