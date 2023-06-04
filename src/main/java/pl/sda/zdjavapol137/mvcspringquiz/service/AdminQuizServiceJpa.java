package pl.sda.zdjavapol137.mvcspringquiz.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.sda.zdjavapol137.mvcspringquiz.mapper.QuizMapper;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.repository.QuizRespository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class AdminQuizServiceJpa implements AdminQuizService {
    private final QuizRespository quizRespository;

    public AdminQuizServiceJpa(QuizRespository quizRespository) {
        this.quizRespository = quizRespository;
    }

    @Override
    public void saveQuiz(Quiz quiz) {
        quizRespository.save(QuizMapper.mapToEntity(quiz));
    }

    @Override
    public List<Quiz> findAllQuizzes() {
        return quizRespository
                .findAll()
                .stream()
                .map(QuizMapper::mapFromEntity)
                // map(e -> QuizMapper.mapFromEntity(e))
                .toList();
    }

    @Override
    public Optional<Quiz> findQuizById(long id) {
        return quizRespository
                .findById(id)
                .map(QuizMapper::mapFromEntity);
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        quizRespository.save(QuizMapper.mapToEntity(quiz));
    }

    @Override
    public void deleteQuizById(long id) {
        quizRespository.deleteById(id);
    }
}
