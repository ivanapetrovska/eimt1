package mk.ukim.finki.eimt.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.eimt.model.Book;
import mk.ukim.finki.eimt.model.dTo.BookDto;
import mk.ukim.finki.eimt.model.exceptions.BookNotFoundException;
import mk.ukim.finki.eimt.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/list")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,@RequestBody BookDto bookDto){
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/mark/{id}")
    public ResponseEntity<Book> markBookAsTaken(@PathVariable Long id){
        Book book = this.bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        if(book == null){
            return ResponseEntity.notFound().build();
        }else{
            this.bookService.markBookAsTaken(id);
            return ResponseEntity.ok(book);
        }
    }

}
