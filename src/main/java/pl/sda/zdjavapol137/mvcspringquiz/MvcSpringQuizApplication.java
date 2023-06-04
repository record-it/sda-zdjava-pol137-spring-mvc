package pl.sda.zdjavapol137.mvcspringquiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Answer;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;
import pl.sda.zdjavapol137.mvcspringquiz.entity.QuizEntity;
import pl.sda.zdjavapol137.mvcspringquiz.entity.User;
import pl.sda.zdjavapol137.mvcspringquiz.mapper.QuizMapper;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.repository.AnswerRepository;
import pl.sda.zdjavapol137.mvcspringquiz.repository.CategoryRepository;
import pl.sda.zdjavapol137.mvcspringquiz.repository.QuizRepository;
import pl.sda.zdjavapol137.mvcspringquiz.repository.UserRepository;
import pl.sda.zdjavapol137.mvcspringquiz.service.AdminQuizService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MvcSpringQuizApplication implements CommandLineRunner {
    private final AdminQuizService service;
    private final CategoryRepository categoryRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public MvcSpringQuizApplication(AdminQuizService service, CategoryRepository categoryRepository, QuizRepository quizRepository, UserRepository userRepository, AnswerRepository answerRepository) {
        this.service = service;
        this.categoryRepository = categoryRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MvcSpringQuizApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // dodaj kilka quizów do serwisu
        final Quiz quiz = Quiz
                .builder()
                .question("2 + 5")
                .correctAnswers(List.of("7"))
                .incorrectAnswers(List.of("5", "6", "8"))
                .title("Dodawanie")
                .build();
        service.saveQuiz(quiz);
        service.saveQuiz(
                Quiz
                        .builder()
                        .question("2 * 5")
                        .correctAnswers(List.of("10"))
                        .incorrectAnswers(List.of("6", "8", "12"))
                        .title("Mnożenie")
                        .build()
        );
        service.saveQuiz(
                Quiz
                        .builder()
                        .question("8 / 2")
                        .correctAnswers(List.of("4"))
                        .incorrectAnswers(List.of("2", "1", "3"))
                        .title("Dzielenie")
                        .build()
        );
        if (categoryRepository.count() < 1) {
            try {
                categoryRepository.save(
                        Category
                                .builder()
                                .rating(3)
                                .name("Matematyka")
                                .build()
                );
            } catch (Exception e) {
                System.out.println("Bład e: " + e.getMessage());
            }

        }
        if (answerRepository.count() < 1) {
            try {
                answerRepository.save(
                        Answer
                                .builder()
                                .userAnswer("7")
                                .quiz(QuizMapper.mapToEntity(service.findQuizById(1).get()))
                                .created(LocalDateTime.now())
                                .build()

                );
            } catch (Exception e) {
                System.out.println("Bład e: " + e.getMessage());
            }
        }
        final Optional<Answer> optionalAnswer = answerRepository.findById(1L);
        if (optionalAnswer.isEmpty()) {
            System.out.println("Brak odpowiedzi");
            return;
        }
        var answer = optionalAnswer.get();
        final Quiz quiz1 = QuizMapper.mapFromEntity(answer.getQuiz());
        var isCorrect = quiz1.isCorrect(List.of(answer.getUserAnswer()));
        System.out.println("Czy poprawna? " + isCorrect);
        var user = userRepository.save(
                User
                        .builder()
                        .email("adam@sda.pl")
                        .password("1234")
                        .build()
        );
        final Optional<QuizEntity> optionalQuiz = quizRepository.findById(1L);
        optionalQuiz.ifPresent(q -> {
            user.setCretedQuizzes(List.of(q));
            var category = categoryRepository.findById(1L).get();
            q.setCategory(category);
            quizRepository.save(q);
            userRepository.save(user);
        });

        var quizzes = quizRepository
                .findByCategoryOrderById(categoryRepository.findById(1L).get());
        System.out.println("Lista quizów kategorii o id 1");
        System.out.println(quizzes);
        //        answerRepository.deleteById(1L);
        //        service.deleteQuizById(1);
        //        System.out.println(answerRepository.findById(1L));

    }
}
