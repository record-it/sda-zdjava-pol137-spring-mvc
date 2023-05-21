package pl.sda.zdjavapol137.mvcspringquiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.zdjavapol137.mvcspringquiz.model.CalculatorData;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // dodaj endpoint /about, który zwróci szablon about.html (utwórz taki w templates)
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    // Dodaj do modelu drugi parametr age, aby wyświetlić w szablonie
    // Witaj <imię>, masz <age> lat.
    @GetMapping("/welcome")
    public String welcome(@RequestParam String name, @RequestParam int age,  Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "welcome";
    }

    @GetMapping("/calc")
    public String caclulate(
            @RequestParam double a,
            @RequestParam double b,
            @RequestParam(defaultValue = "add") String op,
            Model model
    ){
        final CalculatorData calculatorData = CalculatorData
                .builder()
                .operator(op)
                .a(a)
                .b(b)
                .build();
        model.addAttribute("calculatorData", calculatorData);
        return "calculator";
    }
}
