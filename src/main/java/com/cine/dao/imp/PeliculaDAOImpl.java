package com.cine.dao.imp;

import com.cine.dao.PeliculaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cine.dao.ConexionPostgres;
import com.cine.model.domain.Pelicula;

public class PeliculaDAOImpl implements PeliculaDAO {

    // Clase con la logica para buscar las peliculas por el nombre 
    @Override
public List<Pelicula> buscarPeliculasPorNombre(String nombre) {
    List<Pelicula> peliculas = new ArrayList<>();

    String query = "SELECT p.codigo, p.nombre AS nombre_pelicula, p.url, p.imagen, g.nombre AS nombre_genero " +
                    "FROM peliculas p " +
                    "INNER JOIN peliculas_generos pg ON p.codigo = pg.pelicula_codigo " +
                    "INNER JOIN generos g ON pg.genero_id = g.id " +
                    "WHERE p.nombre ILIKE ?";
    
    try (Connection conexion = new ConexionPostgres().getConexion();
         PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
        preparedStatement.setString(1, "%" + nombre + "%");
        
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                // no puede quedar solo como "nombre" ya que hay 2 valores con el mismo nombre, por eso se esta usando nombre_pelicula y nombre_genero
                String nombrePelicula = resultSet.getString("nombre_pelicula"); 
                String url = resultSet.getString("url");
                String genero = resultSet.getString("nombre_genero");
                byte[] imagen = resultSet.getBytes("imagen");
                
                Pelicula pelicula = new Pelicula(codigo, nombrePelicula, url, genero, imagen);
                peliculas.add(pelicula);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    // Mostrar los resultados por nombre
    for (Pelicula pelicula : peliculas) {
        System.out.println("Código: " + pelicula.getCodigo());
        System.out.println("Nombre: " + pelicula.getNombre());
        System.out.println("URL: " + pelicula.getUrl());
        System.out.println("Genero: " + pelicula.getGenero());
        System.out.println("------------------------------");
    }
    
    return peliculas;
}
    // Clase con la logica para buscar las peliculas por el genero
    @Override
    public List<Pelicula> buscarPeliculasPorGenero(int generoId) {
        List<Pelicula> peliculas = new ArrayList<>();
        String query = "SELECT p.codigo, p.nombre, p.url, p.imagen, g.nombre AS genero " +
                       "FROM peliculas p " +
                       "INNER JOIN peliculas_generos pg ON p.codigo = pg.pelicula_codigo " +
                       "INNER JOIN generos g ON pg.genero_id = g.id " +
                       "WHERE g.id = ?";
        
        try (Connection conexion = new ConexionPostgres().getConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, generoId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int codigo = resultSet.getInt("codigo");
                    String nombrePelicula = resultSet.getString("nombre");
                    String url = resultSet.getString("url");
                    String genero = resultSet.getString("genero");
                    byte[] imagen = resultSet.getBytes("imagen");
                    
                    Pelicula pelicula = new Pelicula(codigo, nombrePelicula, url, genero, imagen);
                    peliculas.add(pelicula);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Mostrar las películas por género
        for (Pelicula pelicula : peliculas) {
            System.out.println("Código: " + pelicula.getCodigo());
            System.out.println("Nombre: " + pelicula.getNombre());
            System.out.println("URL: " + pelicula.getUrl());
            System.out.println("Genero: " + pelicula.getGenero());
            System.out.println("------------------------------");
        }
        
        return peliculas;
    }
    

    @Override
    public void insertarPelicula(Pelicula pelicula) {
        String query = "INSERT INTO peliculas (nombre, url, imagen) VALUES (?, ?, ?)";
        
        try (Connection conexion = new ConexionPostgres().getConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, pelicula.getNombre());
            preparedStatement.setString(2, pelicula.getUrl());
            preparedStatement.setBytes(3, pelicula.getImagen());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        String query = "UPDATE peliculas SET nombre = ?, url = ?, imagen = ? WHERE codigo = ?";
        
        try (Connection conexion = new ConexionPostgres().getConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, pelicula.getNombre());
            preparedStatement.setString(2, pelicula.getUrl());
            preparedStatement.setBytes(3, pelicula.getImagen());
            preparedStatement.setInt(4, pelicula.getCodigo());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPelicula(int codigo) {
        String query = "DELETE FROM peliculas WHERE codigo = ?";
        
        try (Connection conexion = new ConexionPostgres().getConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}