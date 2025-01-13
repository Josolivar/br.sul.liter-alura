package br.sul.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Book")
@Table(name = "books")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @Id
    @JsonAlias("id")
    private long id;
    @JsonAlias("title")
    private String title;
    @JsonAlias("authors")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Author> authors;
    @JsonAlias("languages")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language")
    private List<String> languages;
    @JsonAlias("download_count")
    private long downloadCount;

    @Override
    public String toString() {
        return "Livro{" + '\'' +
                "Title = " + title + '\'' +
                ", Authors = " + authors.stream().map(a -> a.getName()).toList() +
                ", Languages = " + languages + '\'' +
                ", Downloads = " + downloadCount + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public long getDownloadCount() {
        return downloadCount;
    }
}