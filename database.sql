-- Crear la base de datos
CREATE DATABASE postgres;

-- Crear la tabla "generos"
CREATE TABLE generos (
    id serial PRIMARY KEY,
    nombre varchar(255) NOT NULL
);

-- Crear la tabla "peliculas"
CREATE TABLE peliculas (
    codigo serial PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    url varchar(255),
    imagen bytea -- Tipo de datos para imágenes
);

-- Crear la tabla "peliculas_generos"
CREATE TABLE peliculas_generos (
    pelicula_codigo integer REFERENCES peliculas(codigo),
    genero_id integer REFERENCES generos(id),
    PRIMARY KEY (pelicula_codigo, genero_id)
);


-- Insetar generos (no es necesario especificar id ya que es un dato tipo 'serial', por lo que PostgreSQL generará automáticamente un valor único para cada nuevo registro que insertes en la tabla) 
INSERT INTO generos (nombre) VALUES ('Accion');


-- Insertar peliculas (no es necesario especificar codigo ya que es un dato tipo 'serial', establecido para iniciar en 10001, por lo que PostgreSQL generará automáticamente un valor único para cada nuevo registro que insertes en la tabla) 
INSERT INTO peliculas (nombre, url) VALUES ('Oppenheimer', 'https://www.filmaffinity.com/es/film294474.html');

-- Crear relacion de la pelicula con algun genero
INSERT INTO peliculas_generos (pelicula_codigo, genero_id) VALUES (10001, 7);





