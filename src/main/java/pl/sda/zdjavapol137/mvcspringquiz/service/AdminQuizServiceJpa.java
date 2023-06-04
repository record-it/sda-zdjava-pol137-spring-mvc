package pl.sda.zdjavapol137.mvcspringquiz.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;
import pl.sda.zdjavapol137.mvcspringquiz.mapper.QuizMapper;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.repository.CategoryRepository;
import pl.sda.zdjavapol137.mvcspringquiz.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class AdminQuizServiceJpa implements AdminQuizService {
    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;

    public AdminQuizServiceJpa(
            QuizRepository quizRepository,
            CategoryRepository categoryRepository
    ) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(QuizMapper.mapToEntity(quiz));
    }

    @Override
    public List<Quiz> findAllQuizzes() {
        return quizRepository
                .findAll()
                .stream()
                .map(QuizMapper::mapFromEntity)
                // map(e -> QuizMapper.mapFromEntity(e))
                .toList();
    }

    @Override
    public Optional<Quiz> findQuizById(long id) {
        return quizRepository
                .findById(id)
                .map(QuizMapper::mapFromEntity);
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        quizRepository.save(QuizMapper.mapToEntity(quiz));
    }

    @Override
    public void deleteQuizById(long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
