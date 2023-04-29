package mk.ukim.finki.eimt.service;

import mk.ukim.finki.eimt.model.Book;
import mk.ukim.finki.eimt.model.dTo.BookDto;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);
    void markBookAsTaken(Long id);
}
