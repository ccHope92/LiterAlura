package com.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.client.ConsumoAPI;
import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GutendexService {

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final String API_URL = "https://gutendex.com/books";

    public void buscarYGuardarLibro(String titulo) {
        String url = API_URL + "?search=" + titulo;
        String json = consumoAPI.obtenerDatos(url);

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode results = rootNode.get("results");

            if (results.isArray()) {
                for (JsonNode bookNode : results) {
                    String bookTitle = bookNode.get("title").asText();
                    String language = bookNode.get("languages").get(0).asText();
                    String authorName = bookNode.get("authors").get(0).get("name").asText();

                    // Evitar duplicados
                    if (!libroRepository.existsByTitulo(bookTitle)) {
                        Autor autor = autorRepository.findByNombre(authorName);
                        if (autor == null) {
                            autor = new Autor(authorName, true); // Suponiendo que todos están vivos
                            autorRepository.save(autor);
                        }

                        Libro libro = new Libro(bookTitle, language, autor);
                        libroRepository.save(libro);
                        System.out.println("Libro guardado: " + libro);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar datos de la API: " + e.getMessage(), e);
        }
    }
}
