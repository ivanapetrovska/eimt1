package mk.ukim.finki.eimt.model;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.eimt.model.enums.BookCategory;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    public Book(String name, BookCategory category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
