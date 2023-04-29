package mk.ukim.finki.eimt.repository;

import mk.ukim.finki.eimt.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    void deleteByName(String name);
}
