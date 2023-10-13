package com.cine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionPostgres {

    public Connection getConexion() {
		// definimos un String que representa la ubicación del driver jdbc
		String DRIVER = "org.postgresql.Driver";

		String URL = "jdbc:postgresql://database-1.c3omiv0w4pyu.us-east-1.rds.amazonaws.com:5432/postgres";
		Connection conexion=null;
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL, "postgres", "X1vOmhKel2OFH4oj8cjZ");
		
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return conexion;
	}
    
}
