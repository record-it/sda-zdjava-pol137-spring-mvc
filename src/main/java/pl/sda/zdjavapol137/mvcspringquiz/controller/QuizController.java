package pl.sda.zdjavapol137.mvcspringquiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    private Map<Long, Quiz> quizzes = new HashMap<>();

    public QuizController() {
        quizzes.put(1L,
                Quiz
                        .builder()
                        .id(1)
                        .title("Matematyka")
                        .question("2 + 5")
                        .correctAnswers(List.of("7"))
                        .incorrectAnswers(List.of("6", "8", "9"))
                        .build()
                );
        quizzes.put(2L,
                Quiz
                        .builder()
                        .id(2)
                        .title("Matematyka")
                        .question("2 * 5")
                        .correctAnswers(List.of("10"))
                        .incorrectAnswers(List.of("20", "5", "12"))
                        .build()
        );
        quizzes.put(3L,
                Quiz
                        .builder()
                        .id(3)
                        .title("Matematyka")
                        .question("9 / 3")
                        .correctAnswers(List.of("3"))
                        .incorrectAnswers(List.of("2", "4", "1"))
                        .build()
        );
    }

    @GetMapping("/fill")
    public String fillQuizForm(@RequestParam Long id){
        return "quiz/fill-form";
    }

    @PostMapping("/fill")
    public String getFillResult(){
        return "quiz/fill-result";
    }
}
