package pl.sda.zdjavapol137.mvcspringquiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data@Builder
@NoArgsConstructor@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userAnswer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private QuizEntity quiz;

    private LocalDateTime created;

    @ManyToOne
    private User user;
}
