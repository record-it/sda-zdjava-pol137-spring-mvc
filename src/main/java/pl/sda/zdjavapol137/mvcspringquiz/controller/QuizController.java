package pl.sda.zdjavapol137.mvcspringquiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.zdjavapol137.mvcspringquiz.dto.RequestQuizAnswerDto;
import pl.sda.zdjavapol137.mvcspringquiz.model.FillQuizViewModel;
import pl.sda.zdjavapol137.mvcspringquiz.service.AdminQuizService;

import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    // wstrzyknij QuizAdminService
    private final AdminQuizService quizService;

    public QuizController(AdminQuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/index")
    public String quizIndex(Model model){
        model.addAttribute("quizzes", quizService.findAllQuizzes());
        return "quiz/index";
    }

    @GetMapping("/fill")
    public String fillQuizForm(@RequestParam Long id, Model model){
        var quiz = quizService.findQuizById(id);
        if (quiz.isEmpty()){
            model.addAttribute("message", "Brak quizu o id: " + id);
            return "error";
        }
        model.addAttribute("quiz", FillQuizViewModel.from(quiz.get()));
        return "quiz/fill-form";
    }

    @PostMapping("/fill")
    public String getFillResult(RequestQuizAnswerDto dto, Model model){
        var quiz = quizService.findQuizById(dto.getId());
        if (quiz.isEmpty()){
            model.addAttribute("message", "Brak quizu o id: " + dto.getId());
            return "error";
        }

        final boolean correct = quiz.get().isCorrect(List.of(dto.getAnswer()));
        model.addAttribute("isCorrect", correct);
        model.addAttribute("quiz", quiz.get());
        return "quiz/fill-result";
    }
}
