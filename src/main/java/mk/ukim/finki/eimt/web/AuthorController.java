package mk.ukim.finki.eimt.web;

import mk.ukim.finki.eimt.model.Author;
import mk.ukim.finki.eimt.model.Country;
import mk.ukim.finki.eimt.model.dTo.AuthorDto;
import mk.ukim.finki.eimt.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/authors")
public class AuthorController {
    private  final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/list")
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto){
        return this.authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<  Author> edit(@PathVariable Long id,@RequestBody AuthorDto authorDto){
        return this.authorService.edit(id, authorDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> delete(@PathVariable Long id){
        this.authorService.deleteById(id);
        if(this.authorService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
