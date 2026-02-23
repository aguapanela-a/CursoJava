package platzi.play.project.plataforma;
import platzi.play.project.contenido.Contenido;

import java.time.LocalDateTime;

public class Usuario {
    public String nombre;
    public String correo;
    private Rol rolUser;
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

    public String ver(Contenido contenido){
        return "El usuario "+ this.nombre + "\n" + contenido.reproducir();
    }

    public String pausar(Contenido contenido){
        return "El usuario "+ this.nombre + "\n" + contenido.pausar();
    }
}
