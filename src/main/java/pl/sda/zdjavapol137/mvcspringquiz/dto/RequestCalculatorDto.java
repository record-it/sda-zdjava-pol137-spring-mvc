package pl.sda.zdjavapol137.mvcspringquiz.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class RequestCalculatorDto {
    @Range(min=-100, max = 100)
    private double a;
    @Range(min=-100, max = 100)
    private double b;
    @Length(max = 3)
    private String op;
}
