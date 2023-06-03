package pl.sda.zdjavapol137.mvcspringquiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Answer;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;
import pl.sda.zdjavapol137.mvcspringquiz.entity.QuizEntity;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.repository.AnswerRepository;
import pl.sda.zdjavapol137.mvcspringquiz.repository.CategoryRepository;
import pl.sda.zdjavapol137.mvcspringquiz.repository.QuizRespository;
import pl.sda.zdjavapol137.mvcspringquiz.service.AdminQuizService;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MvcSpringQuizApplication implements CommandLineRunner {
    private final AdminQuizService service;
    private final CategoryRepository categoryRepository;
    private final QuizRespository quizRespository;

    private final AnswerRepository answerRepository;
    public MvcSpringQuizApplication(AdminQuizService service, CategoryRepository categoryRepository, QuizRespository quizRespository, AnswerRepository answerRepository) {
        this.service = service;
        this.categoryRepository = categoryRepository;
        this.quizRespository = quizRespository;
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
                                .name(null)
                                .build()
                );
            } catch (Exception e) {
                System.out.println("Bład e: " + e.getMessage());
            }

        }
        if (answerRepository.count() < 1){
           try{
                answerRepository.save(
                        Answer
                                .builder()
                                .userAnswer(null)
                                .created(LocalDateTime.now())
                                .build()

                );
            } catch (Exception e){
                System.out.println("Bład e: " + e.getMessage());
            }
        }
        if (quizRespository.count() < 1){
            quizRespository.save(
                    QuizEntity
                            .builder()
                            .title("Dodawanie")
                            .question("2 + 4")
                            .correctAnswers("6")
                            .incorrectAnswers("3|4|7")
                            .build()
            );
        }

        final List<QuizEntity> all = quizRespository.findAll();
        for(var q: all){
            System.out.println(q.getTitle());
            System.out.println(q.getQuestion());
            for(var a: q.getIncorrectAnswers()){
                System.out.println(a);
            }
            for(var a: q.getCorrectAnswers()){
                System.out.println(a);
            }
        }
    }
}
