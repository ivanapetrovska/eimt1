package mk.ukim.finki.eimt.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimt.model.Author;
import mk.ukim.finki.eimt.model.Book;
import mk.ukim.finki.eimt.model.Country;
import mk.ukim.finki.eimt.model.enums.BookCategory;
import mk.ukim.finki.eimt.repository.AuthorRepository;
import mk.ukim.finki.eimt.repository.BookRepository;
import mk.ukim.finki.eimt.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @PostConstruct
    public void init() {
        if (countryRepository.count() != 0 || authorRepository.count() != 0 || bookRepository.count() != 0) {
            return;
        }

        for (int i = 1; i < 31; i++) {
            create(i);
        }
    }
    private void create(int i) {
        Country c = new Country();
        c.setName(String.format("Country %d", i));
        c.setContinent(String.format("Continent %d", i));
        countryRepository.save(c);

        Author a = new Author();
        a.setName(String.format("Name %d", i));
        a.setSurname(String.format("Surname %d", i));
        a.setCountry(c);
        authorRepository.save(a);

        Book b = new Book();
        b.setName(String.format("Name %d", i));
        b.setCategory(BookCategory.values()[i % BookCategory.values().length]);
        b.setAuthor(a);
        b.setAvailableCopies(i);
        bookRepository.save(b);
    }
}
