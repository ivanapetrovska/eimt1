package mk.ukim.finki.eimt.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.eimt.model.Country;
import mk.ukim.finki.eimt.model.dTo.CountryDto;
import mk.ukim.finki.eimt.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping("/list")
    public List<Country> findAll(){
        return countryService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto countryDto){
        return this.countryService.save(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> edit(@PathVariable Long id,@RequestBody CountryDto countryDto){
        return this.countryService.edit(id, countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> delete(@PathVariable Long id){
        this.countryService.deleteById(id);
        if(this.countryService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
