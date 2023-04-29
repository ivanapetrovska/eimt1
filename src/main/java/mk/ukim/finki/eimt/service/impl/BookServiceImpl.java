package mk.ukim.finki.eimt.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.eimt.model.Author;
import mk.ukim.finki.eimt.model.Book;
import mk.ukim.finki.eimt.model.enums.BookCategory;
import mk.ukim.finki.eimt.model.dTo.BookDto;
import mk.ukim.finki.eimt.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.eimt.model.exceptions.BookNotFoundException;
import mk.ukim.finki.eimt.repository.AuthorRepository;
import mk.ukim.finki.eimt.repository.BookRepository;
import mk.ukim.finki.eimt.service.BookService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthor()));
        this.bookRepository.deleteByName(bookDto.getName());
        Book book=new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book=this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        Author author=authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthor()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markBookAsTaken(Long id) {
        Book book=this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        if (book==null){
            return;
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        this.bookRepository.save(book);
    }
}