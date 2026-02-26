package platzi.play.project.contenido;

public interface Promocionable {
    /**
     *
     * Este método es para las clases que quiero que se peudan promocionar, independientemente de u jerarquía
     */
    String promocionar();

    // puedo crear más métodos que unicamente usarán las clases que implementen esta interfaz, y estas clases serán también instancia de esta interfaz
}
