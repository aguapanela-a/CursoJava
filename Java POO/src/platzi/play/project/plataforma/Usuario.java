package platzi.play.project.plataforma;

import platzi.play.project.contenido.Pelicula;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {
    public String nombre;
    public String correo;
    //public String contrasenia;
    public LocalDateTime fechaRegistro;

    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.fechaRegistro = LocalDateTime.now();
    }

    public void registrarse(String nombre, String correo){
        this.nombre = nombre;
        this.correo = correo;
    }

    public String ver(Pelicula pelicula){
        return "El usuario "+ this.nombre + "\n" +pelicula.reproducir();
    }

    public String pausar(Pelicula pelicula){
        return "El usuario "+ this.nombre + "\n" +pelicula.pausar();
    }
}
