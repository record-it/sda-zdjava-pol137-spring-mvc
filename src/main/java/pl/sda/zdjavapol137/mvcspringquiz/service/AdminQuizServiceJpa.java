package pl.sda.zdjavapol137.mvcspringquiz.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.sda.zdjavapol137.mvcspringquiz.mapper.QuizMapper;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class AdminQuizServiceJpa implements AdminQuizService {
    private final QuizRepository quizRepository;

    public AdminQuizServiceJpa(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
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
}
