package pl.sda.zdjavapol137.mvcspringquiz.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.sda.zdjavapol137.mvcspringquiz.mapper.QuizMapper;
import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;
import pl.sda.zdjavapol137.mvcspringquiz.model.QuizViewModel;
import pl.sda.zdjavapol137.mvcspringquiz.service.AdminQuizService;
import pl.sda.zdjavapol137.mvcspringquiz.service.AdminQuizServiceJpa;

import java.util.Optional;


@Controller
@RequestMapping("/admin/quiz")
public class QuizAdminController implements WebMvcConfigurer {

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
            @Valid QuizViewModel quizViewModel,
            BindingResult errors,
            Model model){
        if (errors.hasErrors()){
            return "/quiz/create";
        }
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

    //TODO zaimplemenuj poniższą metodę wyświetlającą szczegóły zadania o id podanym w query
    // np. /admin/quiz/details?id=2
    // 1. uzupełnij parametry metody quizDetails
    // 2. Pobrać z serwisu dany quiz
    // 3. Przekazać quiz jako atrybut do widoku
    // 4. utworzyć widok szczegołów
    @GetMapping("/details")
    public String quizDetails(){
        return "";
    }

    @GetMapping("/delete")
    public String deleteQuiz(@RequestParam long id){
        quizService.deleteQuizById(id);
        return "redirect:/admin/quiz/index";
    }

    @GetMapping("/update")
    public String updateQuizFrom(@RequestParam long id, Model model){
        final Optional<Quiz> optionalQuiz = quizService.findQuizById(id);
        if (optionalQuiz.isEmpty()){
            return "error";
        }
        model.addAttribute("quiz", optionalQuiz.get());
        return "/quiz/update-form";
    }

    @PostMapping("/update")
    public String updateQuiz(@ModelAttribute Quiz updatedQuiz, Model model){
        final Optional<Quiz> optionalQuiz = quizService.findQuizById(updatedQuiz.getId());
        if (optionalQuiz.isEmpty()){
            model.addAttribute("message", "Brak quiz o id: " + updatedQuiz.getId());
            return "error";
        }
        updatedQuiz.setCorrectAnswers(optionalQuiz.get().getCorrectAnswers());
        updatedQuiz.setIncorrectAnswers(optionalQuiz.get().getIncorrectAnswers());
        quizService.updateQuiz(updatedQuiz);
        return "redirect:/admin/quiz/index";
    }
}
