package pl.sda.zdjavapol137.mvcspringquiz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class ExerciseController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public void sendHello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String name = request.getParameter("name");
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        final PrintWriter writer = response.getWriter();
        writer.println("Hi " + name + ", hello from Spring");
    }

    @GetMapping("/powitanie")
    @ResponseBody
    public String sendWelcome(
            @RequestParam(name = "user-name", defaultValue = "anonim") String name,
            @RequestParam(required = false) Integer age
    ){
        return age == null ? "Cześć " + name : "Cześć " + name + ", masz " + age +" lat";
    }

    /**
     * Żadanie w postaci:
     * /calculate?a=3&b=5.6&op=add
     * powinno zwrócić:
     * 3 + 5.6 = 8.6
     * operatory to:
     * add to +
     * sub to -
     * mul to *
     * div to /
     * @return
     */
    @GetMapping("/calculate")
    @ResponseBody
    public String caclulate(
            @RequestParam double a,
            @RequestParam double b,
            @RequestParam(defaultValue = "add") String op
    ){
        return switch (op) {
            case "add" -> String.format("%f + %f = %f", a, b, a + b);
            case "sub" -> String.format("%f - %f = %f", a, b, a - b);
            case "mul" -> String.format("%f * %f = %f", a, b, a * b);
            case "div" -> String.format("%f / %f = %f", a, b, a / b);
            default -> "Brak wyniku, nieznany operator.";
        };
    }
}
