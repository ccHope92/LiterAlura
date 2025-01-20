package com.literalura.LibroController;

import com.literalura.LibroService;
import com.literalura.model.Libro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // Obtener todos los libros sin filtrar por fecha
    @GetMapping
    public Iterable<Libro> obtenerTodosLosLibros() {
        return libroService.obtenerTodosLosLibros(); // Llama al método que devuelve todos los libros
    }
}

