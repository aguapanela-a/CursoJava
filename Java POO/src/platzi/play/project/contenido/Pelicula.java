package platzi.play.project.contenido;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pelicula {
    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    public LocalDate fechaEstreno;
    public double calificacion;
    public boolean disponibilidad;

    public Pelicula(String titulo,String descripcion, int duracion, String genero ){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponibilidad = true;
    }

    public Pelicula(String titulo, String descripcion, int duracion , String genero, boolean disponibilidad, double nota) {
        this(titulo, descripcion, duracion, genero); // ejecuta el contructor de arriba y luego este
        this.disponibilidad = disponibilidad;
        this.calificar(nota);
    }

    // sobrecarda de constructores para hacer que algunos parámetros sean opcionales, como la disponibilidad y calificación

    public String reproducir(){
        return String.format("Está reproduciendo %s", titulo);
    }

    public String pausar(){
        return String.format("Ha pausado %s", titulo);
    }

    public String obtenerFichaTecnica(){
        return String.format("La película  %s publicada el año %tY: %n %s %n Género: %s %n calificaión: %.1f/5", this.titulo,this.fechaEstreno, this.descripcion, this.genero,this.calificacion);
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

}
