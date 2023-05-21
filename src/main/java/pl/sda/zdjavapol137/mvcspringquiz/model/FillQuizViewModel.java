package pl.sda.zdjavapol137.mvcspringquiz.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class FillQuizViewModel {

    private long id;

    private String question;

    private String title;

    private List<String> options;

    private FillQuizViewModel(long id, String question, String title, List<String> options) {
        this.id = id;
        this.question = question;
        this.title = title;
        this.options = options;
    }

    public static FillQuizViewModel from(Quiz quiz){
        List<String> options = new ArrayList<>(quiz.getCorrectAnswers());
        options.addAll(quiz.getIncorrectAnswers());
        Collections.shuffle(options);
        return new FillQuizViewModel(
                quiz.getId(),
                quiz.getQuestion(),
                quiz.getTitle(),
                options
        );
    }
}
