package pl.sda.zdjavapol137.mvcspringquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
