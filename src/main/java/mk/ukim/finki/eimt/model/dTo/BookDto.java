package mk.ukim.finki.eimt.model.dTo;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.eimt.model.enums.BookCategory;

@Data
public class BookDto {

    private String name;

    private BookCategory category;

    private Long author;

    private Integer availableCopies;
}
