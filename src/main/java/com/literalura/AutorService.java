package com.literalura;

import com.literalura.model.Autor;
import com.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Método para obtener autores que estén vivos y cuya fecha de nacimiento sea antes de la fecha proporcionada
    public Iterable<Autor> obtenerAutoresPorFechaNacimiento(String fechaString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de fecha
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        return autorRepository.findByVivoTrueAndFechaNacimientoBefore(fecha);
    }
}

