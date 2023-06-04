package pl.sda.zdjavapol137.mvcspringquiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String title;

    @Column(length = 200)
    private String question;

    private String correctAnswers;

    private String incorrectAnswers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Answer> answers;

    @ManyToOne
    private Category category;

    public void setCorrectAnswers(List<String> items){
        correctAnswers = items.stream().collect(Collectors.joining("|"));
    }

    public List<String> getCorrectAnswers(){
        return Arrays.stream(correctAnswers.split("\\|")).toList();
    }

    public void setIncorrectAnswers(List<String> items) {
        incorrectAnswers = items.stream().collect(Collectors.joining("|"));
    }
    public List<String> getIncorrectAnswers(){
        return Arrays.stream(incorrectAnswers.split("\\|")).toList();
    }

}
