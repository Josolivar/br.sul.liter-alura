package br.sul.liter_alura.repository;

import br.sul.liter_alura.model.Author;
import br.sul.liter_alura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT DISTINCT a FROM Book b JOIN b.authors a")
    List<Author> findAllAuthors();

    @Query("SELECT DISTINCT a FROM Book b JOIN b.authors a WHERE a.birth <= :year AND (a.death IS NULL OR a.death >= :year)")
    List<Author> findLivingAuthorByYear(@Param("year") short year);


    List<Book> findByLanguagesContainingIgnoreCase(String language);
}