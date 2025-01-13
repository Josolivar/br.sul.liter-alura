package br.sul.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Author")
@Table(name = "authors")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonAlias("name")
    @Column(unique=true)
    private String name;
    @JsonAlias("birth_year")
    private short birth;
    @JsonAlias("death_year")
    private short death;
    @JsonAlias("books")
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth year=" + birth +
                ", death year=" + death +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public short getBirth() {
        return birth;
    }

    public short getDeath() { return death; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}