package mk.ukim.finki.eimt.service;

import mk.ukim.finki.eimt.model.Author;
import mk.ukim.finki.eimt.model.Country;
import mk.ukim.finki.eimt.model.dTo.AuthorDto;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findAll();

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, AuthorDto authorDto);
    void deleteById(Long id);
}