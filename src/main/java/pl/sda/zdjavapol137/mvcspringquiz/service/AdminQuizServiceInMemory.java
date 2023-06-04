package pl.sda.zdjavapol137.mvcspringquiz.service;

import org.springframework.stereotype.Service;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.repository.QuizRepositoryInMemory;
import java.util.List;
import java.util.Optional;

@Service("InMemory")
public class AdminQuizServiceInMemory implements AdminQuizService{
    private final QuizRepositoryInMemory repository;

    public AdminQuizServiceInMemory(QuizRepositoryInMemory repository) {
        this.repository = repository;
    }

    @Override
    public void saveQuiz(Quiz quiz) {
        repository.save(quiz);
    }

    @Override
    public List<Quiz> findAllQuizzes() {
        return repository.findAll();
    }

    @Override
    public Optional<Quiz> findQuizById(long id) {
        return repository.findById(id);
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        repository.update(quiz);
    }

    @Override
    public void deleteQuizById(long id) {
        repository.deleteById(id);
    }
}
