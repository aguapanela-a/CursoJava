package platzi.play.project.plataforma;
import platzi.play.project.contenido.Pelicula;
import java.time.LocalDateTime;

public class Usuario {
    public String nombre;
    public String correo;
    public Rol rolUser;
    public LocalDateTime fechaRegistro;

    public Usuario(String nombre, String correo, Rol rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.rolUser = rol;
        this.fechaRegistro = LocalDateTime.now();
    }

    public void registrarse(String nombre, String correo){
        this.nombre = nombre;
        this.correo = correo;
    }

    public String verRol(){
        return "Su rol es " + this.rolUser.getNombre();
    }

    public String ver(Pelicula pelicula){
        return "El usuario "+ this.nombre + "\n" +pelicula.reproducir();
    }

    public String pausar(Pelicula pelicula){
        return "El usuario "+ this.nombre + "\n" +pelicula.pausar();
    }
}
