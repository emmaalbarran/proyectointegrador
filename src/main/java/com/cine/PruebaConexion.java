package com.cine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {
        PruebaConexion conexion = new PruebaConexion();
        Connection conn = conexion.getConexion();

        if (conn != null) {
            System.out.println("Conexión exitosa a la base de datos.");

            // Llamamos a un método para insertar una película
            if (conexion.insertarPelicula(conn, "Barbie", "https://www.filmaffinity.com/es/film506593.html")) {
                System.out.println("Película insertada con éxito.");
            } else {
                System.out.println("Error al insertar la película.");
            }

            // Cerramos la conexión
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }

    public Connection getConexion() {
        String DRIVER = "org.postgresql.Driver";
        String URL = "jdbc:postgresql://database-1.c3omiv0w4pyu.us-east-1.rds.amazonaws.com:5432/postgres";
        Connection conexion = null;
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, "postgres", "X1vOmhKel2OFH4oj8cjZ");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public boolean insertarPelicula(Connection conn, String nombre, String url) {
        String sql = "INSERT INTO peliculas (nombre, url) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, url);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
