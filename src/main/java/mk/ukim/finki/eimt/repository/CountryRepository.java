package mk.ukim.finki.eimt.repository;

import mk.ukim.finki.eimt.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
