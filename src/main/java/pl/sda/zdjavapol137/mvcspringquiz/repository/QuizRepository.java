package pl.sda.zdjavapol137.mvcspringquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;
import pl.sda.zdjavapol137.mvcspringquiz.entity.QuizEntity;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    List<QuizEntity> findByCategoryOrderById(Category category);
}
