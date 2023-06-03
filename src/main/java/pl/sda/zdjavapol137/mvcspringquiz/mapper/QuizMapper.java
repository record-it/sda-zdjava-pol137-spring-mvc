package pl.sda.zdjavapol137.mvcspringquiz.mapper;

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
                .question(model.getQuestion())
                .title(model.getTitle())
                .incorrectAnswers(incorrectAnswers)
                .correctAnswers(correctAnswers)
                .build();
    }
}
