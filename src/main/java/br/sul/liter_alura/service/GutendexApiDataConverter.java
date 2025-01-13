package br.sul.liter_alura.service;

//import br.sul.liter_alura.model.Book;
import br.sul.liter_alura.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

public class GutendexApiDataConverter {

    public static Optional<Book> get(String name) {
        try {
            return Optional.ofNullable(new ObjectMapper().readValue(
                    ApiConsumer.getHttpResponseAsString("https://gutendex.com/books/" + search(name) + "/"),
                    Book.class)
            );

        } catch (JsonProcessingException e) {
            System.out.println("Error in data conversion: " + e.getMessage());
            return Optional.empty();
        } catch(IOException | InterruptedException e) {
            System.out.println("Error in API call: " + e.getMessage());
            return Optional.empty();
        }
    }

    private static int search(String name) {
        String json, str = "";
        int counter = 1;
        try {
            do {
                json = ApiConsumer.getHttpResponseAsString("https://gutendex.com/books/?page=" + counter++);
                if (json.contains(name)) {
                    str = json.split(",\"title\":\"" + name)[0];
                    return Integer.parseInt(str.substring(str.lastIndexOf("\"id\":") + 5, str.length()));
                }
            } while (!str.equals("{\"detail\":\"Invalid page.\"}"));
        } catch(IOException | InterruptedException e) {
            return 0;
        } catch(Exception e) {
            System.out.println("Unknow Exception");
            return -1;
        }
        return 0;
    }
}