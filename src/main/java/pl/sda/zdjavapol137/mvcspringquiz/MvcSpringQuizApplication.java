package pl.sda.zdjavapol137.mvcspringquiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.repository.CategoryRepository;
import pl.sda.zdjavapol137.mvcspringquiz.service.AdminQuizService;

import java.util.List;

@SpringBootApplication
public class MvcSpringQuizApplication implements CommandLineRunner {
    private final AdminQuizService service;
    private final CategoryRepository categoryRepository;
    public MvcSpringQuizApplication(AdminQuizService service, CategoryRepository categoryRepository) {
        this.service = service;
        this.categoryRepository = categoryRepository;
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
            categoryRepository.save(
                    Category
                            .builder()
                            .rating(3)
                            .name("Matematyka")
                            .build()
            );
        }
    }
}
