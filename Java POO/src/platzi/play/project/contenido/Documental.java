package platzi.play.project.contenido;

public class Documental extends Contenido {

    //las clases hijas, además de tener losa tributos y métodos de la clase padre, pueden añadir sus propios atributos o métodos
    private String narrador;

    public Documental(String titulo, String descripcion, int duracion, Genero genero, String narrador) {
        super(titulo, descripcion, duracion, genero);

        //es muy importante que los atributos propios de la clase hija se llamen después del llamado de los atributos de la clase padre, desdués del super()
        this.narrador = narrador;
    }

    public String getNarrador() {
        return narrador;
    }
}
