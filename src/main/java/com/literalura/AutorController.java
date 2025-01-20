package com.literalura;

import com.literalura.model.Autor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    // Método para obtener autores por fecha de nacimiento (se pasa como parámetro)
    @GetMapping("/fecha-nacimiento")
    public Iterable<Autor> obtenerAutoresPorFechaNacimiento(@RequestParam String fechaNacimiento) {
        return autorService.obtenerAutoresPorFechaNacimiento(fechaNacimiento);
    }
}
