package com.literalura;

import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import com.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private GutendexService gutendexService;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("=== LiterAlura ===");
			System.out.println("1. Buscar libro por título");
			System.out.println("2. Listar libros registrados");
			System.out.println("3. Listar autores registrados");
			System.out.println("4. Listar autores vivos en un año");
			System.out.println("5. Buscar libros por idioma");
			System.out.println("6. Salir");
			System.out.print("Selecciona una opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine(); // Limpiar el buffer

			switch (opcion) {
				case 1 -> {
					System.out.print("Ingresa el título del libro: ");
					String titulo = scanner.nextLine();
					gutendexService.buscarYGuardarLibro(titulo);
				}


			case 2 -> libroRepository.findAll().forEach(libro -> System.out.println(libro));
				case 3 -> autorRepository.findAll().forEach(autor -> System.out.println(autor));
				case 4 -> {
					System.out.print("Ingresa el año: ");
					int anio = scanner.nextInt();
					scanner.nextLine(); // Limpiar buffer

					// Crear el objeto LocalDate con el 1 de enero de ese año
					LocalDate fecha = LocalDate.of(anio, 1, 1);
					autorRepository.findByVivoTrueAndFechaNacimientoBefore(fecha).forEach(autor -> System.out.println(autor));
				}
				case 5 -> {
					System.out.print("Ingresa el idioma (ES, EN, FR, PT): ");
					String idioma = scanner.nextLine();
					libroRepository.findByIdioma(idioma).forEach(libro -> System.out.println(libro));
				}
				case 6 -> System.out.println("Saliendo de la aplicación...");
				default -> System.out.println("Opción no válida, intenta de nuevo.");
			}

		} while (opcion != 6);
	}
}
