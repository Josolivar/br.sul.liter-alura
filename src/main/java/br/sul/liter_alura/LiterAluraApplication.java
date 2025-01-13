package br.sul.liter_alura;

import br.sul.liter_alura.model.Author;
import br.sul.liter_alura.model.Book;
import br.sul.liter_alura.repository.BookRepository;
import br.sul.liter_alura.service.GutendexApiDataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    private final String HOME_MENU = "###############################################\n" +
            "\n" +
            "Choose an option:\n" +
            "0 - Exit;\n" +
            "1 - Find the book by title;\n" +
            "2 - List registered books;\n" +
            "3 - List registered authors;\n" +
            "4 - List living authors in a given year;\n" +
            "5 - List books by language.\n" +
            "\n" +
            "###############################################\n\n --> ";

    private Scanner scanner = new Scanner(System.in);

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {

		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        String option;
        do {
            System.out.println(HOME_MENU);
            switch (option = scanner.nextLine()) {
                case "0": break;
                case "1": registerBook(); break;
                case "2": listRegisteredBooks(); break;
                case "3": listRegisteredAuthors(); break;
                case "4": listLivingAuthorsInGivenYear(); break;
                case "5": listBooksByLanguage(); break;
                default: System.out.println("\n+++++ Invalid option, please try again! +++++");
            }
        } while(!option.equals("0"));
        scanner.close();
        System.out.println("\n***** Program completed successfully. *****");
    }
    private void registerBook() {
        System.out.println("\nEnter the name of the book to be searched: ");
        Optional<Book> optionalOfBook = GutendexApiDataConverter.get(scanner.nextLine());
        if(optionalOfBook.isPresent()) {
            Book book = optionalOfBook.get();
            if(book.getAuthors() != null) {
                for(Author author : book.getAuthors()) {
                    if(author.getBooks() == null) {
                        author.setBooks(new ArrayList<>());
                    }
                    author.getBooks().add(book);
                }
            }
            bookRepository.save(book);
        } else System.out.println("Book not found.");
    }

    private void listRegisteredBooks() {
        System.out.println("\nRegistered books:\n");
        bookRepository.findAll().forEach(System.out::println);
    }

    private void listRegisteredAuthors() {
        System.out.println("\nRegistered authors:\n");
        bookRepository.findAllAuthors().forEach(System.out::println);
    }

    private void listLivingAuthorsInGivenYear() {
        System.out.println("\nType the year: ");
        short year = Short.parseShort(scanner.nextLine());
        System.out.println("\nHere are the living authors in " + year + ":\n");
        bookRepository.findLivingAuthorByYear(year).forEach(System.out::println);
    }

    private void listBooksByLanguage() {
        System.out.println("\nType the language: ");
        String language = scanner.nextLine();
        System.out.println("\nHere are the books in " + language + ":\n");
        bookRepository.findByLanguagesContainingIgnoreCase(language).forEach(System.out::println);
    }
}