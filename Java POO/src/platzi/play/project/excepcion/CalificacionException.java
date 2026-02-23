package platzi.play.project.excepcion;

public class CalificacionException extends RuntimeException {
    public CalificacionException(double nota) {
        super("La calificación " + nota + " no está dentro de los rangos aceptado, debe ser entre 0 y 5");
    }
}
