package pl.sda.zdjavapol137.mvcspringquiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "APP_USERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(unique = true)
    public String email;

    public String password;

    @ManyToMany
    private List<QuizEntity> cretedQuizzes;

//    @OneToMany
//    private List<Answer> answers;
}
