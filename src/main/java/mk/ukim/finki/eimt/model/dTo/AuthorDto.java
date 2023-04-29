package mk.ukim.finki.eimt.model.dTo;

import lombok.Data;
import mk.ukim.finki.eimt.model.Country;

@Data
public class AuthorDto {
    private String name;
    private String surname;
    private Country country;
}