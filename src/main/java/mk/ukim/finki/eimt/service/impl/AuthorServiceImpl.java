package mk.ukim.finki.eimt.service.impl;

import mk.ukim.finki.eimt.model.Author;
import mk.ukim.finki.eimt.model.Country;
import mk.ukim.finki.eimt.model.dTo.AuthorDto;
import mk.ukim.finki.eimt.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.eimt.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.eimt.repository.AuthorRepository;
import mk.ukim.finki.eimt.repository.CountryRepository;
import mk.ukim.finki.eimt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }


    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country=this.countryRepository.findById(authorDto.getCountry().getId())
                .orElseThrow(()->new CountryNotFoundException(authorDto.getCountry().getId()));
        this.authorRepository.deleteByName(authorDto.getName());
        Author author=new Author(authorDto.getName(), authorDto.getSurname(), country);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author=this.authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
        Country country=this.countryRepository.findById(authorDto.getCountry().getId())
                .orElseThrow(()->new CountryNotFoundException(authorDto.getCountry().getId()));
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
