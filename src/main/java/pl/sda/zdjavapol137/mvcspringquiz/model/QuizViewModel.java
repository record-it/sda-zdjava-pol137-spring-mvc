package pl.sda.zdjavapol137.mvcspringquiz.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class QuizViewModel {

    @NotEmpty
    @Length(max = 50)
    private String title;

    @NotEmpty
    @Length(min = 3, max = 200)
    private String question;

    private List<@NotEmpty String> options;

    private List<@NotNull Integer> correctOptions;
}
