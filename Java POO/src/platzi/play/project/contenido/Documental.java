package platzi.play.project.contenido;

public class Documental extends Contenido {

    //las clases hijas, además de tener losa tributos y métodos de la clase padre, pueden añadir sus propios atributos o métodos
    private String narrador;

    public Documental(String titulo, String descripcion, int duracion, Genero genero, String narrador, double calificacion) {
        super(titulo, descripcion, duracion, genero, calificacion);

        //es muy importante que los atributos propios de la clase hija se llamen después del llamado de los atributos de la clase padre, desdués del super()
        this.narrador = narrador;
    }

    public String getNarrador() {
        return narrador;
    }

    @Override //notación que indica que estamos sobreescribiendo un método de la clase padre
    public String reproducir() {
        return "Reproduciendo documental " + this.getTitulo() + " narrado por " + this.getNarrador();
    }

    @Override
    public String obtenerFichaTecnica(){
        return String.format("El documental %s publicado el año %tY: %n %s %n Género: %s %n calificación: %.1f/5", this.getTitulo(),this.getFechaEstreno(), this.getDescripcion(), this.getGenero().name(),this.getCalificacion());
    }
}
