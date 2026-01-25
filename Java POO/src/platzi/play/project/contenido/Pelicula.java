package platzi.play.project.contenido;

public class Pelicula {
    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    public int anioEstreno;
    public double calificacion;
    public boolean disponibilidad;

    public String reproducir(){
        return String.format("Se está reproduciendo %s", titulo);
    }

    public String pausar(){
        return String.format("Se ha pausado %s", titulo);
    }

    public String obtenerFichaTecnica(){
        return String.format("La película  %s: %n %s %n Género: %s %n calificaión: %s/5", this.titulo, this.descripcion, this.genero,this.calificacion);
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
