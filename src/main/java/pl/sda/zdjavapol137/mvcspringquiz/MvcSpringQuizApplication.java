package pl.sda.zdjavapol137.mvcspringquiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Answer;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;
import pl.sda.zdjavapol137.mvcspringquiz.entity.QuizEntity;
import pl.sda.zdjavapol137.mvcspringquiz.entity.User;
import pl.sda.zdjavapol137.mvcspringquiz.mapper.QuizMapper;
import pl.sda.zdjavapol137.mvcspringquiz.model.AppUser;
import pl.sda.zdjavapol137.mvcspringquiz.repository.*;
import pl.sda.zdjavapol137.mvcspringquiz.service.AdminQuizService;

import java.time.LocalDateTime;

@SpringBootApplication
public class MvcSpringQuizApplication implements CommandLineRunner {
    private final AdminQuizService service;
    private final CategoryRepository categoryRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final AppUserRepository appUserRepository;

    public MvcSpringQuizApplication(AdminQuizService service, CategoryRepository categoryRepository, QuizRepository quizRepository, UserRepository userRepository, AnswerRepository answerRepository, AppUserRepository appUserRepository) {
        this.service = service;
        this.categoryRepository = categoryRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.appUserRepository = appUserRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MvcSpringQuizApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // dodaj kilka quizów do serwisu
        if (quizRepository.count() > 0) {
            return;
        }
        var category = categoryRepository.save(
                Category
                        .builder()
                        .rating(3)
                        .name("Matematyka")
                        .build()
        );
        quizRepository.save(QuizEntity
                .builder()
                .question("2 + 5")
                .category(category)
                .correctAnswers("7")
                .incorrectAnswers("5|6|8")
                .title("Dodawanie")
                .build());
        quizRepository.save(
                QuizEntity
                        .builder()
                        .question("2 * 5")
                        .category(category)
                        .correctAnswers("10")
                        .incorrectAnswers("6|8|12")
                        .title("Mnożenie")
                        .build()
        );
        quizRepository.save(
                QuizEntity
                        .builder()
                        .question("8 / 2")
                        .correctAnswers("4")
                        .category(category)
                        .incorrectAnswers("2|1|3")
                        .title("Dzielenie")
                        .build()
        );
        var user = userRepository.save(
                User
                        .builder()
                        .email("adam@sda.pl")
                        .password("1234")
                        .build()
        );
        var answer1 = answerRepository.save(
                Answer
                        .builder()
                        .userAnswer("7")
                        .user(user)
                        .quiz(QuizMapper.mapToEntity(service.findQuizById(1).get()))
                        .created(LocalDateTime.now())
                        .build()
        );
        var answer2 = answerRepository.save(
                Answer
                        .builder()
                        .user(user)
                        .userAnswer("7")
                        .quiz(QuizMapper.mapToEntity(service.findQuizById(2).get()))
                        .created(LocalDateTime.now())
                        .build()
        );
        appUserRepository.save(
                AppUser
                        .builder()
                        .roles("USER ADMIN")
                        .emailAddress("ewa@sda.pl")
                        .hashedPassword("$2y$10$caPdtwuIXkPUPwJNkzP88uahebHVMYGW1LCH0jw8RD2/Au18jyVV6")
                        .enabled(true)
                        .build()
        );

        appUserRepository.save(
                AppUser
                        .builder()
                        .roles("USER")
                        .emailAddress("karol@sda.pl")
                        .hashedPassword("$2y$10$caPdtwuIXkPUPwJNkzP88uahebHVMYGW1LCH0jw8RD2/Au18jyVV6")
                        .enabled(false)
                        .build()
        );
    }
}
