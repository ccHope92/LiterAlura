package com.literalura.repository;

import com.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdioma(String idioma);

    boolean existsByTitulo(String titulo);

    // Buscar libros con fecha de publicación antes de una fecha dada
    List<Libro> findByFechaPublicacionBefore(LocalDate fecha);

    // Buscar libros con una fecha de publicación específica
    List<Libro> findByFechaPublicacion(LocalDate fecha);

    List<Libro> findByTitulo(String titulo);
}

