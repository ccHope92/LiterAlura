package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByVivoTrueAndFechaNacimientoBefore(LocalDate fechaNacimiento);

    Autor findByNombre(String nombre);

}


