package com.cine;

import java.util.List;
import java.util.Scanner;

import com.cine.model.domain.Genero;
import com.cine.model.domain.Pelicula;
import com.cine.dao.PeliculaDAO;
import com.cine.dao.GeneroDAO;
import com.cine.dao.imp.PeliculaDAOImpl;
import com.cine.dao.imp.GeneroDAOImpl;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GeneroDAO generoDAO = new GeneroDAOImpl();
        PeliculaDAO peliculaDAO = new PeliculaDAOImpl();

        while (true) {
            System.out.println("Menú de opciones:");
            System.out.println("1. Buscar por nombre");
            System.out.println("2. Buscar por género");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume la línea en blanco

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título de la película: ");
                    String titulo = scanner.nextLine();
                    List<Pelicula> peliculasPorNombre = peliculaDAO.buscarPeliculasPorNombre(titulo);
                    // Mostrar los resultados por nombre
                    break;
                case 2:
                    List<Genero> generos = generoDAO.listarGeneros();
                    System.out.println("Lista de géneros:");
                    for (Genero genero : generos) {
                        System.out.println(genero.getId() + ". " + genero.getNombre());
                    }
                    System.out.print("Ingrese el número del género: ");
                    int generoId = scanner.nextInt();
                    List<Pelicula> peliculasPorGenero = peliculaDAO.buscarPeliculasPorGenero(generoId);
                    // Mostrar las películas por género
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
