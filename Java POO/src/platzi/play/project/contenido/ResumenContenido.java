package platzi.play.project.contenido;

public record ResumenContenido(
        String titulo,
        int duracion,
        Genero genero) {

    public String getResumen(){ // clase inmutable
        //StringBuilder sb = new StringBuilder(); // sb es un objeto de tipo StringBuilder para hacermesto: sb.append("nombre: ").append(titulo)...
        return "Título: "+ titulo + "\n"+
                "Duración: " + duracion + "\n"+
                "Género: " + genero.name().toLowerCase() + "\n";
    }
}
