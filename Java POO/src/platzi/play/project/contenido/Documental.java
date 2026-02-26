package platzi.play.project.contenido;

/**
 * <p>Hereda de {@link Contenido} y a su vez implementa de la interface {@link Promocionable}
 * Haciendo que base su estructura completa de la clase {@link Contenido} pero que solo implemente
 * comportamientos compartidos con otras clases en otras jerarquías por medio de la interface{@link Promocionable}</p>
 */
public class Documental extends Contenido implements Promocionable {

    //las clases hijas, además de tener losa tributos y métodos de la clase padre, pueden añadir sus propios atributos o métodos
    private String narrador;

    public Documental(String titulo, String descripcion, int duracion, Genero genero, String narrador) {
        super(titulo, descripcion, duracion, genero);

        //es muy importante que los atributos propios de la clase hija se llamen después del llamado de los atributos de la clase padre, desdués del super()
        this.narrador = narrador;
    }


    /**
     * {@inheritDoc}
     * @return String
     */
    //si una clase hija pone abstract a un método abstracto NO es obligatorio implementarlo, hasta el nivel de herencia que yo quiera
    @Override //notación que indica que estamos sobreescribiendo un método de la clase padre
    public String reproducir() {
        return "Reproduciendo documental " + this.getTitulo() + " narrado por " + this.getNarrador();
    }

    /**
     * {@inheritDoc}
     * @return un String con la ficha técnica del documental
     */
    @Override
    public String obtenerFichaTecnica(){
        return String.format("El documental %s publicado el año %tY: %n %s %n Género: %s %n calificación: %.1f/5", this.getTitulo(),this.getFechaEstreno(), this.getDescripcion(), this.getGenero().name(),this.getCalificacion());
    }

    /**
     * {@inheritDoc}
     * @return un String de promopción
     */
    @Override
    public String promocionar() {
        return "Descubre el nuevo documental " + this.getTitulo() + " narrado por " + this.getNarrador();
    }

    public String getNarrador() {
        return narrador;
    }
}
