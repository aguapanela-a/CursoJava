package platzi.play.project.contenido;

public record ResumenContenido(        // clase inmutable
        String titulo,
        int duracion,
        Genero genero,
        int idContenio,
        String tipoContenido) {

    public String getResumen(){
        //StringBuilder sb = new StringBuilder(); // sb es un objeto de tipo StringBuilder para hacermesto: sb.append("nombre: ").append(titulo)...
        return "\n"+"Título: "+ titulo + "\n"+
                "Duración: " + duracion + "\n"+
                "Género: " + genero.name().toLowerCase() + "\n"+
                "ID: " + idContenio + "\n"+
                "Tipo: " + tipoContenido + "\n";
    }
}
