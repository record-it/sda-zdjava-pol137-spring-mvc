package pl.sda.zdjavapol137.mvcspringquiz.mapper;

import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;
import pl.sda.zdjavapol137.mvcspringquiz.entity.QuizEntity;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.model.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizMapper {
    public static Quiz mapToQuiz(QuizViewModel model) {
        List<String> correctAnswers = new ArrayList<>();
        List<String> incorrectAnswers = new ArrayList<>();
        for (int i = 0; i < model.getOptions().size(); i++) {
            String option = model.getOptions().get(i);
            if (model.getCorrectOptions().contains(i + 1)) {
                correctAnswers.add(option);
            } else {
                incorrectAnswers.add(option);
            }
        }
        return Quiz
                .builder()
                .categoryId(model.getCategoryId())
                .question(model.getQuestion())
                .title(model.getTitle())
                .incorrectAnswers(incorrectAnswers)
                .correctAnswers(correctAnswers)
                .build();
    }

    public static QuizEntity mapToEntity(Quiz quiz){
        var entity = new QuizEntity();
        entity.setId(quiz.getId());
        entity.setCategory(Category.builder().id(quiz.getCategoryId()).build());
        entity.setTitle(quiz.getTitle());
        entity.setQuestion(quiz.getQuestion());
        entity.setCorrectAnswers(quiz.getCorrectAnswers());
        entity.setIncorrectAnswers(quiz.getIncorrectAnswers());
        return entity;
    }

    public static Quiz mapFromEntity(QuizEntity entity){
        return Quiz
                .builder()
                .title(entity.getTitle())
                .id(entity.getId())
                .incorrectAnswers(entity.getIncorrectAnswers())
                .correctAnswers(entity.getCorrectAnswers())
                .question(entity.getQuestion())
                .build();
    }
}
