package pl.sda.zdjavapol137.mvcspringquiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.List;

@Data
@Builder
public class Quiz {

    private String title;

    private long id;

    private String question;

    private List<String> incorrectAnswers;

    private List<String> correctAnswers;

    private long categoryId;

    public boolean isCorrect(List<String> answers){
        HashSet<String> correctSet = new HashSet<>(correctAnswers);
        HashSet<String> answerSet=new HashSet<>(answers);
        return correctSet.equals(answerSet);
    }
}
