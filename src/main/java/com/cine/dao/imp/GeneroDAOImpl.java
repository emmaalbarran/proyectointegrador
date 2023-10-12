package com.cine.dao.imp;

import com.cine.dao.GeneroDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cine.dao.ConexionPostgres;
import com.cine.model.domain.Genero;

public class GeneroDAOImpl implements GeneroDAO {

    @Override
    public List<Genero> listarGeneros() {
        List<Genero> generos = new ArrayList<>();
        String query = "SELECT id, nombre FROM generos";
        
        try (Connection conexion = new ConexionPostgres().getConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    
                    Genero genero = new Genero(id, nombre);
                    generos.add(genero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return generos;
    }

    @Override
    public Genero obtenerGeneroPorId(int id) {
        Genero genero = null;
        String query = "SELECT id, nombre FROM generos WHERE id = ?";
        
        try (Connection conexion = new ConexionPostgres().getConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    genero = new Genero(id, nombre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return genero;
    }

    @Override
    public void crearGenero(Genero genero) {
        String query = "INSERT INTO generos (nombre) VALUES (?)";
        
        try (Connection conexion = new ConexionPostgres().getConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, genero.getNombre());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
