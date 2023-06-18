package pl.sda.zdjavapol137.mvcspringquiz.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
    private long id;

    private String title;

    private int editionYear;
}
