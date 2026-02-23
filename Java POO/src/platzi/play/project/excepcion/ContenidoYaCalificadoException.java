package platzi.play.project.excepcion;

public class ContenidoYaCalificadoException extends RuntimeException {
    public ContenidoYaCalificadoException(String titulo) {
        super("El contenido '" + titulo + "' ya tiene una calificación.");
    }
}