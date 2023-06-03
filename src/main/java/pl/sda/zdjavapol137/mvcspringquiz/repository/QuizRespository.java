package pl.sda.zdjavapol137.mvcspringquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.zdjavapol137.mvcspringquiz.entity.QuizEntity;

public interface QuizRespository extends JpaRepository<QuizEntity, Long> {
}
