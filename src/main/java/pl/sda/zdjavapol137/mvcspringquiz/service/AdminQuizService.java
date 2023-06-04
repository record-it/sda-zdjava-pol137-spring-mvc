package pl.sda.zdjavapol137.mvcspringquiz.service;

import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface AdminQuizService {
    void saveQuiz(Quiz quiz);

    List<Quiz> findAllQuizzes();

    Optional<Quiz> findQuizById(long id);


    void updateQuiz(Quiz quiz);

    void deleteQuizById(long id);

    void saveCategory(Category category);

    List<Category> findAllCategories();
}
