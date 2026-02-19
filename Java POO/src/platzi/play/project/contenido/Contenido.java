package platzi.play.project.contenido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Contenido {  //als e runa calse abstacte nose puede instanciar
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

    public Contenido(String titulo, String descripcion, int duracion, Genero genero, double calificacion ){
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

    public List<Idioma> obtenerIdiomas(){
        return Collections.unmodifiableList(this.idioma);
    }


    // sobrecarga de constructores para hacer que algunos parámetros sean opcionales, como la disponibilidad y calificación

    public abstract String reproducir();

    public String pausar(){
        return String.format("Ha pausado %s", titulo);
    }

    public abstract String obtenerFichaTecnica();

    public String calificar(double nota){
            if (nota >= 0 && nota <= 5){
                this.calificacion = nota;
                return String.format("Usted ha calificado la película %s con %.1f estrellas de 5. Gracias por sus comentarios", this.titulo, this.calificacion);
            }else{
                return "Ingrese un número válido";
            }
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

}
