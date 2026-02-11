package platzi.play.project.excepcion;

/**
 * Excepción de tipo "Unchecked" que ocurre cuando se intenta duplicar una película.
 */
public class PeliculaExistenteException extends RuntimeException {

    public PeliculaExistenteException(String titulo) {
        // super() envía el mensaje al constructor de RuntimeException,
        // quedando disponible para ser consultado mediante el metodo .getMessage()
        super("El contenido " + titulo + " ya existe");
    }
}