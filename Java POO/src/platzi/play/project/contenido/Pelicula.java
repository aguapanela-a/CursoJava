package platzi.play.project.contenido;

/**
 * Clase que hereda de {@link Contenido} e implementa los dos métodos abstractos de la clase padre
 */
public class Pelicula extends  Contenido{

    public Pelicula(String titulo, String descripcion, int duracion, Genero genero) { //contructor de película
        super(titulo, descripcion, duracion, genero); // y estos datos del contructor de Pelicula se los vamos a enviar a Contenido
    }

    @Override
    public String reproducir() {
        return "Reproduciendo película "+this.getTitulo();
    }

    @Override
    public String obtenerFichaTecnica() {
        return String.format("La película  %s publicada el año %tY: %n %s %n Género: %s %n calificación: %.1f/5", this.getTitulo(),this.getFechaEstreno(), this.getDescripcion(), this.getGenero().name(),this.getCalificacion());
    }
}
