package mk.ukim.finki.eimt.service;

import mk.ukim.finki.eimt.model.Country;
import mk.ukim.finki.eimt.model.dTo.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);
    List<Country> findAll();
    Optional<Country> save(CountryDto countryDto);
    Optional<Country> edit(Long id, CountryDto countryDto);
    void deleteById(Long id);
}
