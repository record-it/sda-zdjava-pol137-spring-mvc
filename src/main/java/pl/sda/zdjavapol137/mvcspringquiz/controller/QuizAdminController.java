package pl.sda.zdjavapol137.mvcspringquiz.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.zdjavapol137.mvcspringquiz.mapper.QuizMapper;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.model.QuizViewModel;
import pl.sda.zdjavapol137.mvcspringquiz.service.AdminQuizService;



@Controller
@RequestMapping("/admin/quiz")
public class QuizAdminController {

    private final AdminQuizService quizService;

    public QuizAdminController(AdminQuizService quizService) {
        this.quizService = quizService;
    }

    // zadeklaruj metode pod ścieżką '/admin/quiz/create',
    // która zwróci widok z formularzem quizu '/quiz/create'
    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("quizViewModel", new QuizViewModel());
        return "/quiz/create";
    }

    @PostMapping("/create")
    public String createQuiz(
            @ModelAttribute @Valid QuizViewModel quizViewModel,
            Model model){
        final Quiz quiz = QuizMapper.mapToQuiz(quizViewModel);
        quizService.saveQuiz(quiz);
        model.addAttribute("quizzes", quizService.findAllQuizzes());
        return "/quiz/index";
    }

    @GetMapping("/index")
    public String quizIndex(Model model){
        //przekaż do modelu atrybut "quizzes" z listą wszystkich quizów
        model.addAttribute("quizzes", quizService.findAllQuizzes());
        return "/quiz/admin-index";
    }
}
