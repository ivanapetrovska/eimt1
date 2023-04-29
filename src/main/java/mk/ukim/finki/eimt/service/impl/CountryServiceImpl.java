package mk.ukim.finki.eimt.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.eimt.model.Country;
import mk.ukim.finki.eimt.model.dTo.CountryDto;
import mk.ukim.finki.eimt.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.eimt.repository.CountryRepository;
import mk.ukim.finki.eimt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        return Optional.of(this.countryRepository.save(new Country(countryDto.getName(), countryDto.getContinent())));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country=this.countryRepository.findById(id).orElseThrow(()->new CountryNotFoundException(id));
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}