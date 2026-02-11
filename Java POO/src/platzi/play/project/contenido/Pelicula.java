package platzi.play.project.contenido;

public class Pelicula extends  Contenido{

    public Pelicula(String titulo, String descripcion, int duracion, Genero genero, double calificacion) { //contructor de película
        super(titulo, descripcion, duracion, genero, calificacion); // y estos datos del contructor de Pelicula se los vamos a enviar a Contenido
    }
}
