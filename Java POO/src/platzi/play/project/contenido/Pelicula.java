package platzi.play.project.contenido;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pelicula {
    private String titulo;
    private int idPeli;
    private String descripcion;
    private int duracion;
    private Genero genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponibilidad;

    public Pelicula(String titulo,String descripcion, int duracion, Genero genero ){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponibilidad = true;
    }

    public Pelicula(String titulo, String descripcion, int duracion , Genero genero, boolean disponibilidad, double nota) {
        this(titulo, descripcion, duracion, genero); // ejecuta el contructor de arriba y luego este
        this.disponibilidad = disponibilidad;
        this.calificar(nota);
    }

    // sobrecarga de constructores para hacer que algunos parámetros sean opcionales, como la disponibilidad y calificación

    public String reproducir(){
        return String.format("Está reproduciendo %s", titulo);
    }

    public String pausar(){
        return String.format("Ha pausado %s", titulo);
    }

    public String obtenerFichaTecnica(){
        return String.format("La película  %s publicada el año %tY: %n %s %n Género: %s %n calificación: %.1f/5", this.titulo,this.fechaEstreno, this.descripcion, this.genero,this.calificacion);
    }

    public String calificar(double nota){
            if (nota >= 0 && nota <= 5){
                this.calificacion = nota;
                return String.format("Usted ha calificado la película %s con %.1f estrellas de 5. Gracias poor sus comentarios", this.titulo, this.calificacion);
            }else{
                return "Ingrese un número válido";
            }
    }

    public boolean popular(){
        return this.calificacion >= 4;  //devuelvem,e el resukltado de esta comparación
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIdPeli() {
        return idPeli;
    }

    public void setIdPeli(int idPeli) {
        this.idPeli = idPeli;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
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
