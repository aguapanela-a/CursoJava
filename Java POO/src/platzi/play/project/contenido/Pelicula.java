package platzi.play.project.contenido;

public class Pelicula extends  Contenido{

    public Pelicula(String titulo, String descripcion, int duracion, Genero genero, double calificacion) { //contructor de película
        super(titulo, descripcion, duracion, genero, calificacion); // y estos datos del contructor de Pelicula se los vamos a enviar a Contenido
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
