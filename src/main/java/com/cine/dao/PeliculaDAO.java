package com.cine.dao;

import java.util.List;
import com.cine.model.domain.Pelicula;

public interface PeliculaDAO {
    List<Pelicula> buscarPeliculasPorNombre(String nombre);
    List<Pelicula> buscarPeliculasPorGenero(int generoId);
    void insertarPelicula(Pelicula pelicula);
    void actualizarPelicula(Pelicula pelicula);
    void eliminarPelicula(int codigo);
}
