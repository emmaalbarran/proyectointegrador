package com.cine.dao;

import com.cine.model.domain.Genero;
import java.util.List;

public interface GeneroDAO {
    List<Genero> listarGeneros();
    Genero obtenerGeneroPorId(int id);
    void crearGenero(Genero genero);
}

