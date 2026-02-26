package platzi.play.project.contenido;

import platzi.play.project.excepcion.CalificacionException;
import platzi.play.project.excepcion.ContenidoYaCalificadoException;
import platzi.play.project.excepcion.PeliculaExistenteException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h1>Contenido</h1>
 * <p>Esta es la clase base de la familia de contenidos, y como es abstracta no se pued instanciar
 * Contiene métodos abstractos tales como:
 * {@code reproducir()}
 * que su comportamiento lo define y/o implementa cada clase hija de Contenido</p>
 *
 * @author Erick Buitrago
 * @version 1.0.0
 * @since 2026
 *
 * @see #reproducir()
 */
public abstract class Contenido {  //al ser una clase abstracta no se puede instanciar
    private String titulo;
    private int idContenido;
    private String descripcion;
    private int duracion;
    private Genero genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponibilidad;
    private List<Idioma> idioma;

    // crear el atributo calidad y hacer un enum para cada uno

    public Contenido(String titulo, String descripcion, int duracion, Genero genero){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponibilidad = true;
        this.idioma = new ArrayList<>();
        this.calificacion = calificacion;
    }


    public void establecerIdiomas(List<Idioma> idiomasValidos){
        idioma.addAll(idiomasValidos);
    }

    /**
     * Proporciona la lista de idiomas  disponibles
     * * @return Una {@link List} de objetos de tipo {@link Idioma}
     * La lista es inmutable para proteger la integridad de los datos
     */
    public List<Idioma> obtenerIdiomas(){
        return Collections.unmodifiableList(this.idioma);
    }

    /**
     *
     * {@code reproducir()} método abstracto
     */
    public abstract String reproducir(); //método abstracto para que las hijas lo implementen como quieran, no solo copiar el comportamiento del método de la clase padre

    public String pausar(){
        return String.format("Ha pausado %s", titulo);
    }

    public abstract String obtenerFichaTecnica();

    public boolean isCalificada(){
        return this.calificacion > 0;
    }

    /**
     * Registra una nota para el contenido si no ha sido calificado previamente.
     * @param nota Valor decimal entre 0 y 5 que representa la calificación.
     * @throws CalificacionException Si el valor está fuera del rango [0, 5].
     * @throws ContenidoYaCalificadoException Si el contenido ya posee una nota asignada.
     * @see #isCalificada()
     */

    public void calificar(double nota) {
        if (nota < 0 || nota > 5) {
            throw new CalificacionException(nota);
        }
        if (isCalificada()) {
            throw new ContenidoYaCalificadoException(this.getTitulo());
        }
        this.calificacion = nota;
    }

    public boolean popular(){
        return this.calificacion >= 4;  //devuelve el resultado de esta comparación
    }

    public String getTitulo() {
        return titulo;
    }

    public int getId() {
        return idContenido;
    }

    public void setIdContenido(int idC) {
        this.idContenido = idC;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setCalificacion(double nota) {this.calificacion = nota;}

}
