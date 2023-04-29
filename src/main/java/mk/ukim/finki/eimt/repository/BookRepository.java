package mk.ukim.finki.eimt.repository;

import jdk.jfr.Registered;
import mk.ukim.finki.eimt.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    void deleteByName(String name);
    void deleteById(Long id);
}
